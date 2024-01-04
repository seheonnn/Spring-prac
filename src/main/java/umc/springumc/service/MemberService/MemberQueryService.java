package umc.springumc.service.MemberService;

import umc.springumc.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
}
