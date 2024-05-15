package umc.springumc.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.springumc.security.dto.UserRegisterRequestDto;
import umc.springumc.security.dto.UserRegisterResponseDto;
import umc.springumc.security.entity.User;
import umc.springumc.security.exception.UserErrorCode;
import umc.springumc.security.exception.UserExceptionHandler;
import umc.springumc.security.jwt.dto.JwtDto;
import umc.springumc.security.jwt.util.JwtUtil;
import umc.springumc.security.repository.user.UserRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserRegisterResponseDto register(UserRegisterRequestDto request) {

		if (!request.password().equals(request.passwordCheck()))
			throw new UserExceptionHandler(UserErrorCode.PASSWORD_NOT_EQUAL);

		String encodedPw = passwordEncoder.encode(request.password());
		User newUser = request.toEntity(encodedPw);

		return UserRegisterResponseDto.from(userRepository.save(newUser));
	}

	public JwtDto reissueToken(String refreshToken) {
		return jwtUtil.reissueToken(refreshToken);
	}
}
