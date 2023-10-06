package com.teamsix.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teamsix.dto.ArticleContent;
import com.teamsix.dto.ArticleDto;
import com.teamsix.dto.VersionTrackingResponse;
import com.teamsix.entity.User;

public interface UserService {
	
	public ResponseEntity<String> registerUser(User user);
	public ResponseEntity<User> getUserByUserId(Long userId);
	public ResponseEntity<List<User>> getUserByRole(String role);
	public ResponseEntity<String> createArticle(Long userId, ArticleContent articleContent);
	public ResponseEntity<List<ArticleDto>> getAllArticlesByUser(Long userId);
	public ResponseEntity<VersionTrackingResponse> createVersion(String articleId, ArticleDto articleDto );
	public ResponseEntity<List<ArticleDto>> compareArticleByVersions(String articleId, Integer versionId1,
			Integer versionId2);
	public ResponseEntity<ArticleDto> getLatestArticleByArticleId(String articleId);
	public ResponseEntity<List<ArticleDto>> compareLatestArticle(String articleId, Integer versionId);
	public ResponseEntity<List<ArticleDto>> getAllApprovedArticle();

    ResponseEntity<User> getUserByUsername(String username);
}
