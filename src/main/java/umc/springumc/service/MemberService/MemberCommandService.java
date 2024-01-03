package umc.springumc.service.MemberService;

import umc.springumc.domain.Member;
import umc.springumc.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
