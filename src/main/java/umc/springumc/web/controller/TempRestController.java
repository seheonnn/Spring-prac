package umc.springumc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.springumc.base.Code;
import umc.springumc.base.ResponseDto;
import umc.springumc.converter.TempConverter;
import umc.springumc.service.TempService.TempQueryService;
import umc.springumc.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ResponseDto<TempResponse.TempTestDTO> testAPI(){
        return ResponseDto.onSuccess(TempConverter.toTempTestDTO(), Code.OK);
    }
    @GetMapping("/exception")
    public ResponseDto<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ResponseDto.onSuccess(TempConverter.toTempExceptionDTO(flag), Code.OK);
    }
}
