package umc.springumc.domain.dao;

import java.util.List;

import umc.springumc.domain.Article;

public interface ArticleRepository {

	Article create(Article article);

	List<Article> findAll();

	Article findById(int id);

	Article update(Article employee);

	void deleteById(int id);
}
