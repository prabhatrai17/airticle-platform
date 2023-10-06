package com.example.versioncontrolservice.controller;

import com.example.versioncontrolservice.Dto.ArticleDto;
import com.example.versioncontrolservice.Dto.VersionTrackingDto;
import com.example.versioncontrolservice.Dto.VersionTrackingResponse;
import com.example.versioncontrolservice.entity.VersionTracking;
import com.example.versioncontrolservice.service.VersionTrackingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/version")
@AllArgsConstructor
public class VersionTrackingController {
    private final VersionTrackingService versionTrackingService;

    @PostMapping("/article/{articleId}")
    public ResponseEntity<VersionTrackingResponse> createVersion(@PathVariable String articleId, @RequestBody ArticleDto article){
        return new ResponseEntity<>(versionTrackingService.createVersion(articleId, article), HttpStatus.CREATED);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<ArticleDto>> trackVersionByArticleId(@PathVariable String articleId){
        return ResponseEntity.ok(versionTrackingService.trackVersionByArticleId(articleId));
    }
    @GetMapping("/article/{articleId}/version1/{versionId1}/version2/{versionId2}")
    public ResponseEntity<List<ArticleDto>> compareArticle( @PathVariable String articleId ,@PathVariable Integer versionId1, @PathVariable Integer  versionId2 ){
        return ResponseEntity.ok(versionTrackingService.compareArticle(articleId, versionId1, versionId2));
    }

}
