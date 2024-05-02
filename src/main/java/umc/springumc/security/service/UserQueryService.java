package umc.springumc.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.springumc.security.entity.User;
import umc.springumc.security.exception.UserErrorCode;
import umc.springumc.security.exception.UserExceptionHandler;
import umc.springumc.security.repository.user.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserQueryService {

	private final UserRepository userRepository;

	public User findByUserName(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UserExceptionHandler(UserErrorCode.USER_NOT_FOUND));
	}
}
