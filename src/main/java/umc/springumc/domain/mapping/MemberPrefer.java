package umc.springumc.domain.mapping;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.springumc.apiPayload.global.BaseEntity;
import umc.springumc.domain.FoodCategory;
import umc.springumc.domain.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) // 지연 로딩(LAZY) : 실제 객체가 사용될 때 해당 쿼리가 한 번 더 나감. / 즉시 로딩(EAGER) : 한 번의 쿼리로 모두 가져옴
	@JoinColumn(name = "member_id")
	private Member member;
	// 대부분의 비즈니스 로직에서 Member 와 MemberPrefer 관계가 같이 사용된다면 EAGER 가 유리

	// 단방향 mapping
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private FoodCategory foodCategory;

	public void setMember(Member member) {
		if (this.member != null)
			member.getMemberPreferList().remove(this);
		this.member = member;
		member.getMemberPreferList().add(this);
	}

	public void setFoodCategory(FoodCategory foodCategory) {
		this.foodCategory = foodCategory;
	}
}
