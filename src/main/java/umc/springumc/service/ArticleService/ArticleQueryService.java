package umc.springumc.service.ArticleService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.springumc.domain.Article;
import umc.springumc.domain.dao.ArticleRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleQueryService {

	private final ArticleRepository articleRepository;

	public Article read() {
		return articleRepository.findById(2);
	}

	public List<Article> readList() {
		return articleRepository.findAll();
	}
}
