package umc.springumc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springumc.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
