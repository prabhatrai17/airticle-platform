package com.teamsix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VersionTrackingResponse {
    private String articleId;
    private Integer versionId;
    private String title;
    private String content;
    private String status;
}
