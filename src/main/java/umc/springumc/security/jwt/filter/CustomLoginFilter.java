package umc.springumc.security.jwt.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.apiPayload.global.ApiResponse;
import umc.springumc.security.jwt.dto.JwtDto;
import umc.springumc.security.jwt.exception.SecurityErrorCode;
import umc.springumc.security.jwt.userdetails.CustomUserDetails;
import umc.springumc.security.jwt.util.HttpResponseUtil;
import umc.springumc.security.jwt.util.JwtUtil;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Override
	public Authentication attemptAuthentication(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response
	) throws AuthenticationException {
		log.info("[*] Login Filter");

		Map<String, Object> requestBody;
		try {
			requestBody = getBody(request);
		} catch (IOException e) {
			throw new AuthenticationServiceException("Error of request body.");
		}

		log.info("[*] Request Body : " + requestBody);

		String username = (String)requestBody.get("username");
		String password = (String)requestBody.get("password");

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password,
			null);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain chain,
		@NonNull Authentication authentication) throws IOException {
		log.info("[*] Login Success");

		CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();

		log.info("[*] Login with " + customUserDetails.getUsername());

		JwtDto jwtDto = new JwtDto(
			jwtUtil.createJwtAccessToken(customUserDetails),
			jwtUtil.createJwtRefreshToken(customUserDetails)
		);

		HttpResponseUtil.setSuccessResponse(response, HttpStatus.CREATED, jwtDto);
	}

	@Override
	protected void unsuccessfulAuthentication(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull AuthenticationException failed) throws IOException {

		log.info("[*] Login Fail");

		// String errorMessage;
		// if (failed instanceof BadCredentialsException) {
		// 	errorMessage = "Bad credentials";
		// } else if (failed instanceof LockedException) {
		// 	errorMessage = "Account is locked";
		// } else if (failed instanceof DisabledException) {
		// 	errorMessage = "Account is disabled";
		// } else if (failed instanceof UsernameNotFoundException) {
		// 	errorMessage = "Account not found";
		// } else if (failed instanceof AuthenticationServiceException) {
		// 	errorMessage = "Error occurred while parsing request body";
		// } else {
		// 	errorMessage = "Authentication failed";
		// }
		// HttpResponseUtil.setErrorResponse(
		// 	response, HttpStatus.UNAUTHORIZED,
		// 	ApiResponse.onFailure(
		// 		HttpStatus.BAD_REQUEST.name(),
		// 		errorMessage
		// 	)
		// );

		SecurityErrorCode errorCode;
		if (failed instanceof BadCredentialsException) {
			errorCode = SecurityErrorCode.BAD_CREDENTIALS;
		} else if (failed instanceof LockedException || failed instanceof DisabledException) {
			errorCode = SecurityErrorCode.FORBIDDEN;
		} else if (failed instanceof UsernameNotFoundException) {
			errorCode = SecurityErrorCode.USER_NOT_FOUND;
		} else if (failed instanceof AuthenticationServiceException) {
			errorCode = SecurityErrorCode.INTERNAL_SECURITY_ERROR;
		} else {
			errorCode = SecurityErrorCode.UNAUTHORIZED;
		}
		HttpResponseUtil.setErrorResponse(
			response,
			errorCode.getHttpStatus(),
			ApiResponse.onFailure(
				errorCode.getCode(),
				errorCode.getMessage()
			)
		);
	}

	private Map<String, Object> getBody(HttpServletRequest request) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String line;

		try (BufferedReader bufferedReader = request.getReader()) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}

		String requestBody = stringBuilder.toString();
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(requestBody, Map.class);
	}
}

