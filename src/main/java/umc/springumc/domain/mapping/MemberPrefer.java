package umc.springumc.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springumc.domain.FoodCategory;
import umc.springumc.domain.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩(LAZY) : 실제 객체가 사용될 때 해당 쿼리가 한 번 더 나감. / 즉시 로딩(EAGER) : 한 번의 쿼리로 모두 가져옴
    @JoinColumn(name = "member_id")
    private Member member;
    // 대부분의 비즈니스 로직에서 Member 와 MemberPrefer 관계가 같이 사용된다면 EAGER 가 유리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;
}
