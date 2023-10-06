package com.teamsix.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {
	
	private Long srNo;
	private String articleId;
	private String title;
	private String content;
	private Status status;
	private LocalDateTime creationDate;
	private Date lastModifiedDate;
	private Integer currentVersionId;
	private UserDto user;
	
	
	

}
