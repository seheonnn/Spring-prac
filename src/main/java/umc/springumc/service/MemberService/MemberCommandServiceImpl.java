package umc.springumc.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springumc.apiPayload.code.status.ErrorStatus;
import umc.springumc.apiPayload.exception.handler.FoodCategoryHandler;
import umc.springumc.converter.MemberConverter;
import umc.springumc.converter.MemberPreferConverter;
import umc.springumc.domain.FoodCategory;
import umc.springumc.domain.Member;
import umc.springumc.domain.mapping.MemberPrefer;
import umc.springumc.repository.FoodCategoryRepository;
import umc.springumc.repository.MemberRepository;
import umc.springumc.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 연관 관계 설정은 converter 보단 service 에서 하는 것이 좋음
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}