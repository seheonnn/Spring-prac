package umc.springumc.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.springumc.apiPayload.ApiResponse;
import umc.springumc.domain.Article;
import umc.springumc.service.ArticleService.ArticleQueryService;
import umc.springumc.service.ArticleService.ArticleService;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleService articleService;
	private final ArticleQueryService articleQueryService;

	@PostMapping("/create")
	public ApiResponse<String> create() {
		return ApiResponse.onSuccess(articleService.create());
	}

	@GetMapping("/read")
	public ApiResponse<Article> read() {
		return ApiResponse.onSuccess(articleQueryService.read());

	}

	@PostMapping("/update")
	public ApiResponse<Article> update() {
		return ApiResponse.onSuccess(articleService.update());
	}

	@PostMapping("/delete")
	public ApiResponse<String> delete() {
		return ApiResponse.onSuccess(articleService.delete());
	}

	@GetMapping("/readlist")
	public ApiResponse<List<Article>> readList() {
		return ApiResponse.onSuccess(articleQueryService.readList());
	}
}
