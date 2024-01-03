package umc.springumc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.springumc.base.Code;
import umc.springumc.base.ResponseDto;
import umc.springumc.converter.MemberConverter;
import umc.springumc.domain.Member;
import umc.springumc.service.MemberService.MemberCommandService;
import umc.springumc.web.dto.MemberRequestDTO;
import umc.springumc.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ResponseDto<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ResponseDto.onSuccess(MemberConverter.toJoinResultDTO(member), Code.OK);
    }
}