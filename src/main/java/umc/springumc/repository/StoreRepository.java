package umc.springumc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springumc.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
