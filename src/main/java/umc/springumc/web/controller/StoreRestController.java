package umc.springumc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springumc.apiPayload.ApiResponse;
import umc.springumc.converter.StoreConverter;
import umc.springumc.domain.Review;
import umc.springumc.service.StoreService.StoreCommandService;
import umc.springumc.validation.annotaion.ExistMember;
import umc.springumc.validation.annotaion.ExistStore;
import umc.springumc.web.dto.StoreRequestDTO;
import umc.springumc.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
}
