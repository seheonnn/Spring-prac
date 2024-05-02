package umc.springumc.security.repository.user;

import java.util.Optional;

import umc.springumc.security.entity.User;

public interface UserRepository {

	Optional<User> findByUsername(String username);

	User save(User user);
}
