package umc.springumc.converter;

import umc.springumc.domain.Review;
import umc.springumc.web.dto.StoreRequestDTO;
import umc.springumc.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {
    public static Review toReview(StoreRequestDTO.ReveiwDTO request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
