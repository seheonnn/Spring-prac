package umc.springumc.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springumc.domain.common.BaseEntity;
import umc.springumc.domain.enums.Gender;
import umc.springumc.domain.enums.MemberStatus;
import umc.springumc.domain.enums.SocialType;
import umc.springumc.domain.mapping.MemberAgree;
import umc.springumc.domain.mapping.MemberMission;
import umc.springumc.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder // 빌더 패턴을 위함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL 사용 시 enum 순서 바뀌면 에러 발생
    @Column(columnDefinition = "VARCHAR(10)") // 컬럼 타입을 직접 지정
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'") // MySQL 에서 문자열은 무조건 ''로 감싸야 함.
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Column(nullable = false, length = 50)
    private String email;

    private Integer point;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // CascadeType.ALL : Member 삭제 시 연결된 데이터 모두 삭제
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
