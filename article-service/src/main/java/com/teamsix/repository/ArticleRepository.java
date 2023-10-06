package com.teamsix.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teamsix.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	public List<Article> findByArticleId(String articleId);
	
	@Query(value = "select count(*) from article where article_id =:articleId", nativeQuery = true)
	public List<Article> getCountOfArticleByArticleId(String articleId);
	
	@Query(value = "select * from article where article_id =:articleId and current_version_id =:versionId", nativeQuery = true)
	public Article getArticleByArticleIdAndVersionId(String articleId, int versionId);
	
	@Query(value = "select * from article where status = 0", nativeQuery = true)
	public List<Article> getApprovedArticle();
	
	public List<Article> findByUserId(Long userId);
}
