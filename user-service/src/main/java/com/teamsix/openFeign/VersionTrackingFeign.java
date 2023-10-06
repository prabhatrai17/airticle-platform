package com.teamsix.openFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamsix.dto.ArticleDto;
import com.teamsix.dto.VersionTrackingResponse;


@FeignClient(name = "VERSION-TRACKING-SERVICE")
public interface VersionTrackingFeign {
	
	@PostMapping("/api/version/article/{articleId}")
    public ResponseEntity<VersionTrackingResponse> createVersion(@PathVariable String articleId, @RequestBody ArticleDto article);
	
	@GetMapping("/api/version/article/{articleId}/version1/{versionId1}/version2/{versionId2}")
    public ResponseEntity<List<ArticleDto>> compareArticle( @PathVariable String articleId ,@PathVariable Integer versionId1, @PathVariable Integer  versionId2 );

	@GetMapping("/api/version/article/compare/latest/{articleId}/version2/{versionId}")
    public ResponseEntity<List<ArticleDto>> compareWithLatestArticle(@PathVariable String articleId, @PathVariable Integer versionId);
}
