package umc.springumc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springumc.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
