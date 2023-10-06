package com.teamsix.controller;

import java.util.List;

import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamsix.dto.ArticleContent;
import com.teamsix.dto.ArticleDto;
import com.teamsix.dto.VersionTrackingResponse;
import com.teamsix.entity.User;
import com.teamsix.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		return this.userService.registerUser(user);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable Long userId){
		return this.userService.getUserByUserId(userId);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		return this.userService.getUserByUsername(username);
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<User>> getAllUserByRole(@PathVariable String role){
		return this.userService.getUserByRole(role);
	}
	
	@PostMapping("/{userId}/create")
	public ResponseEntity<String> createArticle(@PathVariable Long userId, @RequestBody ArticleContent articleContent){
		return this.userService.createArticle(userId, articleContent);
	}
	
	@PostMapping("/create/version/{articleId}")
	public ResponseEntity<VersionTrackingResponse> createVersion(@PathVariable String articleId, @RequestBody ArticleDto aticleDto){
		return this.userService.createVersion(articleId, aticleDto);
	}
	
	
	@GetMapping("/{userId}/article")
	public ResponseEntity<List<ArticleDto>> getArticleByUser(@PathVariable Long userId){
		return this.userService.getAllArticlesByUser(userId);
	}
	
	@GetMapping("/article/{articleId}/version1/{versionId1}/version2/{versionId2}")
	public ResponseEntity<List<ArticleDto>> compareArticle(@PathVariable String articleId, @PathVariable Integer versionId1, @PathVariable Integer versionId2){
		return this.userService.compareArticleByVersions(articleId, versionId1, versionId2);
	}
	
	@GetMapping("/article/latest/{articleId}")
	public ResponseEntity<ArticleDto> getLatestArticle(@PathVariable String articleId){
		return this.userService.getLatestArticleByArticleId(articleId);
	}
	
	@GetMapping("/article/compare/latest/{articleId}/version2/{versionId}")
	public ResponseEntity<List<ArticleDto>> compareWithLatestArticle(@PathVariable String articleId, @PathVariable Integer versionId){
		return this.userService.compareLatestArticle(articleId, versionId);
	}

	@GetMapping("/article/approved")
	public ResponseEntity<List<ArticleDto>> getApprovedArticle(){
		return this.userService.getAllApprovedArticle();
	}
	
	

}
