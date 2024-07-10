package umc.springumc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.apiPayload.global.ApiResponse;
import umc.springumc.security.annotation.UserResolver;
import umc.springumc.security.dto.UserRegisterRequestDto;
import umc.springumc.security.dto.UserRegisterResponseDto;
import umc.springumc.security.entity.User;
import umc.springumc.security.jwt.dto.JwtDto;
import umc.springumc.security.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping("/join")
	public ApiResponse<UserRegisterResponseDto> register(@Valid @RequestBody UserRegisterRequestDto request) {
		log.info("============== join ===============");
		return ApiResponse.onSuccess(userService.register(request));
	}

	@GetMapping("/reissue")
	public ApiResponse<JwtDto> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
		return ApiResponse.onSuccess(userService.reissueToken(refreshToken));
	}

	@GetMapping("/test")
	public ApiResponse<String> register(@UserResolver User user) {
		return ApiResponse.onSuccess(user.getUsername());
	}
}
