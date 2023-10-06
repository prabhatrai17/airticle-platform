package com.teamsix.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.teamsix.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamsix.dto.ArticleDto;
import com.teamsix.dto.User;
import com.teamsix.dto.UserDto;
import com.teamsix.entity.Article;
import com.teamsix.openFeign.UserFeign;
import com.teamsix.repository.ArticleRepository;
import com.teamsix.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserFeign userFeign;
	
	@Override
	public ResponseEntity<String> createArticle(Long userId, Article article) {
		try {
			article.setUserId(userId);
			this.articleRepository.save(article);
			return ResponseEntity.ok("Article created!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> getAllArticle() {
		try {
			 List<Article> articleList = this.articleRepository.findAll();
			 return ResponseEntity.ok(articleList.stream().map(this::fromEntityToDto).collect(Collectors.toList()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> getArticleByArticleId(String articleId) {
		try {
			List<Article> article = this.articleRepository.findByArticleId(articleId);
			List<ArticleDto> articleDtoList = article.stream().map(this::fromEntityToDto).collect(Collectors.toList());
			if(Objects.nonNull(article)) return ResponseEntity.ok(articleDtoList);
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public int getCountOfArticlesByArticleId(String articleId) {
		List<Article> articleList = this.articleRepository.findAll();
		return articleList.stream().filter(resp-> resp.getArticleId().equals(articleId)).collect(Collectors.toList()).size();
	}

	@Override
	public ResponseEntity<ArticleDto> createVersionOfArticle(String articleId, Article article) {
		try {
			List<Article> articleObj = this.articleRepository.findByArticleId(articleId);
			if(Objects.nonNull(articleObj)) {
				int versionCount = getCountOfArticlesByArticleId(articleId);
				article.setCreationDate(articleObj.get(0).getCreationDate());
				System.out.println(articleObj.get(0).getCreationDate());
				article.setArticleId(articleId);
				article.setStatus(articleObj.get(articleObj.size()-1).getStatus());
				article.setUserId(articleObj.get(0).getUserId());
				article.setCurrentVersionId(versionCount+1);
				this.articleRepository.save(article);
				ArticleDto articleDto = fromEntityToDto(article);
				return ResponseEntity.ok(articleDto);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<ArticleDto> updateArticle(String articleId, int versionId, Article article) {
		try {
			Article articleObj = this.articleRepository.getArticleByArticleIdAndVersionId(articleId, versionId);
			if(Objects.nonNull(articleObj)) {
				articleObj.setTitle(article.getTitle());
				articleObj.setContent(article.getContent());
				this.articleRepository.save(articleObj);
				ArticleDto articleDto = fromEntityToDto(articleObj);
				return ResponseEntity.ok(articleDto);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> compareArticleByArticleId(String articleId, int versionId1, int versionId2) {
		try {
			List<Article> articleList = this.articleRepository.findByArticleId(articleId);
			List<Article> compareArticleList = new ArrayList<>();
			
			if(Objects.nonNull(articleList)) {
				for(Article article: articleList) {
					if(article.getCurrentVersionId() == versionId1) compareArticleList.add(article);
					if(article.getCurrentVersionId() == versionId2) compareArticleList.add(article);
				}
				List<ArticleDto> articleDtoList = compareArticleList.stream().map(this::fromEntityToDto).collect(Collectors.toList());
				return ResponseEntity.ok(articleDtoList);
			}
			
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ArticleDto fromEntityToDto(Article article) {
		User user = this.userFeign.getUserByUserId(article.getUserId()).getBody();
		UserDto userDto = new UserDto(user.getUserId(), user.getUsername(), user.getRole());
		return new ArticleDto(article.getSrNo(), article.getArticleId(), article.getTitle(), article.getContent(), article.getStatus(), article.getCreationDate(), article.getLastModifiedDate(), article.getCurrentVersionId(), userDto);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> getArticlesByUser(Long userId) {
		try {
			List<Article> articleList = this.articleRepository.findByUserId(userId);
			if(!articleList.isEmpty()) {
				List<ArticleDto> articleDtoList = articleList.stream().map(this::fromEntityToDto).collect(Collectors.toList());
				return ResponseEntity.ok(articleDtoList);
			}
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ArticleDto> getLatestArticleByArticleId(String articleId) {
		try {
			List<Article> articleList = this.articleRepository.findByArticleId(articleId);
			if(!articleList.isEmpty()) {
				Article article = articleList.get(articleList.size()-1);
				ArticleDto articleDto = fromEntityToDto(article);
				return ResponseEntity.ok(articleDto);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> compareWithLatestArticle(String articleId, Integer versionId) {
		try {
			ArticleDto latestArticleDto = getLatestArticleByArticleId(articleId).getBody();
			List<ArticleDto> compareWithLatestArticleDtoList = compareArticleByArticleId(articleId, latestArticleDto.getCurrentVersionId(), versionId).getBody();
			return ResponseEntity.ok(compareWithLatestArticleDtoList);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ArticleDto>> getAllApprovedArticle() {
		try {
			List<Article> approvedArticleList = this.articleRepository.getApprovedArticle();
			return ResponseEntity.ok(approvedArticleList.stream().map(this::fromEntityToDto).collect(Collectors.toList()));
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Article setArticleStatus(String articleId, String status) {
		ArticleDto article= this.getLatestArticleByArticleId(articleId).getBody();
		if(status.equalsIgnoreCase("approved"))
		article.setStatus(Status.approved);
		if(status.equalsIgnoreCase("rejected"))
			article.setStatus(Status.rejected);
		else
			article.setStatus(Status.in_progress);
       System.out.println(article.getStatus());
	      List<Article> articleList=articleRepository.findByArticleId(articleId);
		  Article finalArticle=articleList.get(articleList.size()-1);
		  finalArticle.setStatus(Status.approved);
        return articleRepository.save(finalArticle);

	}


}
