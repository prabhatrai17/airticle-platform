package com.teamsix.workflowservice.openFeign;

import com.teamsix.workflowservice.payload.ArticleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ARTICLE-SERVICE",path = "api/article")
public interface ArticleFeign {
    @GetMapping("/{articleId}")
    public ResponseEntity<List<ArticleDto>> getArticleById(@PathVariable String articleId);

    @PostMapping("/{articleId}")
    public ResponseEntity<ArticleDto> createVersion(@PathVariable String articleId, @RequestBody ArticleDto article);
}
