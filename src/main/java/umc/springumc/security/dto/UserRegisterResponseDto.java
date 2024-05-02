package umc.springumc.security.dto;

import lombok.Builder;
import umc.springumc.security.entity.User;

@Builder
public record UserRegisterResponseDto(
	Long id,
	String username,
	String email
) {

	public static UserRegisterResponseDto from(User user) {
		return UserRegisterResponseDto.builder()
			.id(user.getId())
			.username(user.getUsername())
			.email(user.getEmail())
			.build();
	}
}
