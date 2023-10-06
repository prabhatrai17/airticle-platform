package com.example.versioncontrolservice.openFeign;

import com.example.versioncontrolservice.Dto.ArticleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="ARTICLE-SERVICE", path="/api/article")
public interface ArticleFeignClient {
    @PostMapping("/{articleId}")
    public ResponseEntity<ArticleDto> createVersion(@PathVariable String articleId, @RequestBody ArticleDto article);
    @GetMapping("/{articleId}")
    public ResponseEntity<List<ArticleDto>> getArticleById(@PathVariable String articleId);

    @GetMapping("/{articleId}/version1/{versionId1}/version2/{versionId2}")
    public ResponseEntity<List<ArticleDto>> compareArticles(@PathVariable String articleId, @PathVariable int versionId1, @PathVariable int versionId2);

}
