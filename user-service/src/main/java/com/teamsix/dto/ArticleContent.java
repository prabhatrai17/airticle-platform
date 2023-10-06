package com.teamsix.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


public class ArticleContent {
	
	private Long srNo;
	private String articleId = java.util.UUID.randomUUID().toString();
	private String title;
	private String content;
	private Status status = Status.in_progress;
	@CreationTimestamp
	private LocalDateTime creationDate;
	@UpdateTimestamp
	private Date lastModifiedDate;
	private Integer currentVersionId = 1;
	private Long userId;
	
	

	public ArticleContent() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ArticleContent(Long srNo, String articleId, String title, String content, Status status,
			LocalDateTime creationDate, Date lastModifiedDate, Integer currentVersionId, Long userId) {
		super();
		this.srNo = srNo;
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
		this.currentVersionId = currentVersionId;
		this.userId = userId;
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


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}



}
