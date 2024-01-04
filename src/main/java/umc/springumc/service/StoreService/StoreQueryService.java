package umc.springumc.service.StoreService;

import org.springframework.data.domain.Page;
import umc.springumc.domain.Review;
import umc.springumc.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review> getReviewList(Long StoreId, Integer page);
}
