package umc.springumc.security.jwt.util;

import static umc.springumc.security.jwt.exception.TokenErrorCode.*;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.security.jwt.dto.JwtDto;
import umc.springumc.security.jwt.exception.SecurityCustomException;
import umc.springumc.security.jwt.userdetails.CustomUserDetails;

@Slf4j
@Component
public class JwtUtil {

	private final SecretKey secretKey;
	private final Long accessExpMs;
	private final Long refreshExpMs;
	private final RedisUtil redisUtil;
	private static final String USERNAME = "username";
	private static final String IS_STAFF = "is_staff";

	public JwtUtil(
		@Value("${jwt.secret}") String secret,
		@Value("${jwt.token.access-expiration-time}") Long access,
		@Value("${jwt.token.refresh-expiration-time}") Long refresh,
		RedisUtil redis) {

		secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
			Jwts.SIG.HS256.key().build().getAlgorithm());
		accessExpMs = access;
		refreshExpMs = refresh;
		redisUtil = redis;
	}

	public String createJwtAccessToken(CustomUserDetails customUserDetails) {
		Instant issuedAt = Instant.now();
		Instant expiration = issuedAt.plusMillis(accessExpMs);

		return Jwts.builder()
			.header()
			.add("alg", "HS256")
			.add("typ", "JWT")
			.and()
			.claim(USERNAME, customUserDetails.getUsername())
			.claim(IS_STAFF, customUserDetails.getStaff())
			.issuedAt(Date.from(issuedAt))
			.expiration(Date.from(expiration))
			.signWith(secretKey)
			.compact();
	}

	public String createJwtRefreshToken(CustomUserDetails customUserDetails) {
		Instant issuedAt = Instant.now();
		Instant expiration = issuedAt.plusMillis(refreshExpMs);

		String refreshToken = Jwts.builder()
			.header()
			.add("alg", "HS256")
			.add("typ", "JWT")
			.and()
			.claim(USERNAME, customUserDetails.getUsername())
			.claim(IS_STAFF, customUserDetails.getStaff())
			.issuedAt(Date.from(issuedAt))
			.expiration(Date.from(expiration))
			.signWith(secretKey)
			.compact();

		redisUtil.saveAsValue(
			customUserDetails.getUsername() + "_refresh_token",
			refreshToken,
			refreshExpMs,
			TimeUnit.MILLISECONDS
		);
		return refreshToken;
	}

	public JwtDto reissueToken(String refreshToken) throws SignatureException {
		try {
			validateRefreshToken(refreshToken);
			log.info("[*] Valid RefreshToken");

			CustomUserDetails tempCustomUserDetails = new CustomUserDetails(
				// getId(refreshToken),
				// getEmail(refreshToken),
				getUsername(refreshToken),
				null,
				// getAuthority(refreshToken)
				isStaff(refreshToken)
			);

			return new JwtDto(
				createJwtAccessToken(tempCustomUserDetails),
				createJwtRefreshToken(tempCustomUserDetails)
			);
		} catch (IllegalArgumentException iae) {
			throw new SecurityCustomException(INVALID_TOKEN, iae);
		} catch (ExpiredJwtException eje) {
			throw new SecurityCustomException(TOKEN_EXPIRED, eje);
		}
	}

	public String resolveAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			log.warn("[*] No Token in req");
			return null;
		}

		log.info("[*] Token exists");
		return authorization.split(" ")[1];
	}

	public void validateRefreshToken(String refreshToken) {
		// refreshToken 유효성 검증
		String username = getUsername(refreshToken);

		//redis에 refreshToken 있는지 검증
		if (!redisUtil.hasKey(username + "_refresh_token")) {
			log.warn("[*] case : Invalid refreshToken");
			throw new SecurityCustomException(TOKEN_EXPIRED);
		}
	}

	public String getUsername(String token) {
		return getClaims(token).get(USERNAME, String.class);
	}

	public boolean isStaff(String token) {
		return getClaims(token).get(IS_STAFF, Boolean.class);
	}

	public Boolean isExpired(String token) {
		// 여기서 토큰 형식 이상한 것도 걸러짐
		return getClaims(token).getExpiration().before(Date.from(Instant.now()));
	}

	public Long getExpTime(String token) {
		return getClaims(token).getExpiration().getTime();
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
			throw new SecurityCustomException(INVALID_TOKEN, e);
		} catch (SignatureException e) {
			throw new SecurityCustomException(TOKEN_SIGNATURE_ERROR, e);
		}
	}
}
