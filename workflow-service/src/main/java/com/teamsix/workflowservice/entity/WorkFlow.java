package com.teamsix.workflowservice.entity;

import com.teamsix.workflowservice.payload.ArticleDto;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
public class WorkFlow {
    @Id
    //@GeneratedValue
    @UuidGenerator
    String id;
    //String articleId;
    @Embedded
    ArticleDto article;
    //String editorCurrentStatus;
    String editorUserId;
    String reviewerUserId;
    String submissionDate;
    String approvalDate;
    String rejectionReason;
    @OneToOne
    ReviewerFeedback reviewerFeedback;
    @OneToMany
    List<EditorChanges> editorChangesList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(String articleId) {
//        this.articleId = articleId;
//    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    public String getEditorUserId() {
        return editorUserId;
    }

    public void setEditorUserId(String editorUserId) {
        this.editorUserId = editorUserId;
    }

    public String getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(String reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public ReviewerFeedback getReviewerFeedback() {
        return reviewerFeedback;
    }

    public void setReviewerFeedback(ReviewerFeedback reviewerFeedback) {
        this.reviewerFeedback = reviewerFeedback;
    }

    public List<EditorChanges> getEditorChangesList() {
        return editorChangesList;
    }

    public void setEditorChangesList(List<EditorChanges> editorChangesList) {
        this.editorChangesList = editorChangesList;
    }

    public WorkFlow(String id, ArticleDto article, String editorUserId, String reviewerUserId, String submissionDate, String approvalDate, String rejectionReason, ReviewerFeedback reviewerFeedback, List<EditorChanges> editorChangesList) {
        this.id = id;
      //  this.articleId = articleId;
        this.article = article;
        this.editorUserId = editorUserId;
        this.reviewerUserId = reviewerUserId;
        this.submissionDate = submissionDate;
        this.approvalDate = approvalDate;
        this.rejectionReason = rejectionReason;
        this.reviewerFeedback = reviewerFeedback;
        this.editorChangesList = editorChangesList;
    }

    public WorkFlow() {
    }

    @Override
    public String toString() {
        return "WorkFlow{" +
                "id='" + id + '\'' +
               // ", articleId='" + articleId + '\'' +
                ", article=" + article +
                ", editorUserId='" + editorUserId + '\'' +
                ", reviewerUserId='" + reviewerUserId + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", approvalDate='" + approvalDate + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", reviewerFeedback=" + reviewerFeedback +
                ", editorChangesList=" + editorChangesList +
                '}';
    }
}
