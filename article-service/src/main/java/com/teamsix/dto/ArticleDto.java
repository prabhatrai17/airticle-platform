package com.teamsix.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.teamsix.util.Status;

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
	private Status status;;
	private LocalDateTime creationDate;
	private Date lastModifiedDate;
	private Integer CurrentVersionId;
	private UserDto user;
	
	
}
