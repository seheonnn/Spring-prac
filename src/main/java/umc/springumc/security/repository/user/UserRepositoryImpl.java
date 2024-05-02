package umc.springumc.security.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import umc.springumc.security.entity.User;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> findByUsername(String username) {
		return userJpaRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userJpaRepository.save(user);
	}
}
