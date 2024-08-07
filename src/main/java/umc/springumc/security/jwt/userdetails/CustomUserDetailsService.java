package umc.springumc.security.jwt.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.springumc.security.entity.User;
import umc.springumc.security.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// User user = userRepository.findByUsername(username)
		// 	.orElseThrow(() -> new SecurityCustomException(SecurityErrorCode.USER_NOT_FOUND));

		final User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Account not found"));

		log.info("[*] User found : " + user.getUsername());

		return new CustomUserDetails(user.getUsername(), user.getPassword(), user.isStaff());
	}
}
