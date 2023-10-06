package com.teamsix.openFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamsix.dto.ArticleContent;
import com.teamsix.dto.ArticleDto;

@FeignClient(name = "ARTICLE-SERVICE")
public interface ArticleFeign {
	
	@GetMapping("/api/article/getString")
	public ResponseEntity<String> getString();
	
	@PostMapping("/api/article/user/{userId}")
	public ResponseEntity<String> addArticle(@PathVariable Long userId, @RequestBody ArticleContent articleContent);
	
	@GetMapping("/api/article/user/{userId}")
	public ResponseEntity<List<ArticleDto>> getAllArticlesByUser(@PathVariable Long userId);
	
	@GetMapping("/api/article/latest/{articleId}")
	public ResponseEntity<ArticleDto> getLatestArticle(@PathVariable String articleId);
	
	@GetMapping("/api/article/approved")
	public ResponseEntity<List<ArticleDto>> getApprovedArticle();

}
