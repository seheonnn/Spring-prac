package umc.springumc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.springumc.apiPayload.ApiResponse;
import umc.springumc.converter.TempConverter;
import umc.springumc.service.TempService.TempQueryService;
import umc.springumc.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

	private final TempQueryService tempQueryService;

	@GetMapping("/test")
	public ApiResponse<TempResponse.TempTestDTO> testAPI() {
		return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
	}

	@GetMapping("/exception")
	public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
		tempQueryService.CheckFlag(flag);
		return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
	}

	@GetMapping("/health")
	public String healthCheck() {
		return "I'm healthy!";
	}
}
