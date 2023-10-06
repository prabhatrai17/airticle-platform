package com.example.versioncontrolservice.service;

import com.example.versioncontrolservice.Dto.ArticleDto;
import com.example.versioncontrolservice.Dto.VersionTrackingDto;
import com.example.versioncontrolservice.Dto.VersionTrackingResponse;

import java.util.List;

public interface VersionTrackingService {


    List<ArticleDto> trackVersionByArticleId(String arcticleId);


    List<ArticleDto> compareArticle(String articleId, Integer versionId1, Integer versionId2);

    VersionTrackingResponse createVersion(String articleId, ArticleDto article);
}
