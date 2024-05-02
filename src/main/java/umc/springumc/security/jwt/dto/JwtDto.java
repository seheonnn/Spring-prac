package umc.springumc.security.jwt.dto;

public record JwtDto(
	String accessToken,
	String refreshToken
) {
}
