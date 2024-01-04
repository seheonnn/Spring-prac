package umc.springumc.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import umc.springumc.domain.Review;
import umc.springumc.web.dto.StoreRequestDTO;
import umc.springumc.web.dto.StoreResponseDTO;

public class StoreConverter {
	public static Review toReview(StoreRequestDTO.ReveiwDTO request) {
		return Review.builder()
			.title(request.getTitle())
			.score(request.getScore())
			.body(request.getBody())
			.build();
	}

	public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
		return StoreResponseDTO.CreateReviewResultDTO.builder()
			.reviewId(review.getId())
			.createdAt(LocalDateTime.now())
			.build();
	}

	public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
		return StoreResponseDTO.ReviewPreViewDTO.builder()
			.ownerNickname(review.getMember().getName())
			.score(review.getScore())
			.createdAt(review.getCreatedAt().toLocalDate())
			.body(review.getBody())
			.build();
	}

	// TODO
	// FIXME
	public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
		List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
			.map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

		return StoreResponseDTO.ReviewPreViewListDTO.builder()
			.isLast(reviewList.isLast())
			.isFirst(reviewList.isFirst())
			.totalPage(reviewList.getTotalPages())
			.totalElements(reviewList.getTotalElements())
			.listSize(reviewPreViewDTOList.size())
			.reviewList(reviewPreViewDTOList)
			.build();
	}
}
