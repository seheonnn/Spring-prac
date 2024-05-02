package umc.springumc.security.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.springumc.security.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
