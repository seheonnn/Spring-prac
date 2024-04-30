package umc.springumc.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import umc.springumc.domain.Article;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

	// @PersistenceContext // @PersistenceContext 로도 의존성 주입 가능
	private final EntityManager entityManager;

	@Override
	public Article create(Article article) {
		entityManager.persist(article);
		return article;
	}

	@Override
	public List<Article> findAll() {
		TypedQuery<Article> query = entityManager.createQuery("From Article", Article.class);
		return query.getResultList();
	}

	@Override
	public Article findById(int id) {
		return entityManager.find(Article.class, id);
	}

	@Override
	public Article update(Article article) {
		return entityManager.merge(article);
	}

	@Override
	public void deleteById(int id) {
		entityManager.remove(entityManager.find(Article.class, id));
	}
}
