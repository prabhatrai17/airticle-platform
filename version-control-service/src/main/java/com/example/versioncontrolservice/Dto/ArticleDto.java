package com.example.versioncontrolservice.Dto;

import com.example.versioncontrolservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


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
	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleDto(Long srNo, String articleId, String title, String content, Status status, LocalDateTime creationDate,
			Date lastModifiedDate, Integer currentVersionId, UserDto user) {
		super();
		this.srNo = srNo;
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.currentVersionId = currentVersionId;
		this.user = user;
	}
	public Long getSrNo() {
		return srNo;
	}
	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Integer getCurrentVersionId() {
		return currentVersionId;
	}
	public void setCurrentVersionId(Integer currentVersionId) {
		this.currentVersionId = currentVersionId;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
    
    

}
