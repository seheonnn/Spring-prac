package umc.springumc.service.StoreService;

import umc.springumc.domain.Review;
import umc.springumc.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request);
}
