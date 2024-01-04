package umc.springumc.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springumc.converter.StoreConverter;
import umc.springumc.domain.Review;
import umc.springumc.repository.MemberRepository;
import umc.springumc.repository.ReviewRepository;
import umc.springumc.repository.StoreRepository;
import umc.springumc.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;
    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}
