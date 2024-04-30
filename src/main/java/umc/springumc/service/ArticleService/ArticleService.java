package umc.springumc.service.ArticleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.springumc.domain.Article;
import umc.springumc.domain.dao.ArticleRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public String create() {
		Article article1 = Article.builder()
			.title("제목 테스트1")
			.content("제목 테스트1")
			.build();

		Article article2 = Article.builder()
			.title("제목 테스트2")
			.content("제목 테스트2")
			.build();

		Article article3 = Article.builder()
			.title("제목 테스트3")
			.content("제목 테스트3")
			.build();

		articleRepository.create(article1);
		articleRepository.create(article2);
		articleRepository.create(article3);
		return "성공";
	}

	public Article update() {
		Article updated = Article.builder()
			.title("제목 테스트 수정")
			.content("제목 테스트 수정")
			.build();

		return articleRepository.update(updated);
	}

	public String delete() {
		articleRepository.deleteById(1);
		return "삭제 성공";
	}
}
