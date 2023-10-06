package com.teamsix.workflowservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class ReviewerFeedback {
    @Id
    //@GeneratedValue
    @UuidGenerator
    String id;
    String reviewerUserId;
    String reviewStatus;//approved,rejected,inprogress
    String reviewFeedback;//any suggestion, fixes
    String reviewTimeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(String reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewFeedback() {
        return reviewFeedback;
    }

    public void setReviewFeedback(String reviewFeedback) {
        this.reviewFeedback = reviewFeedback;
    }

    public String getReviewTimeStamp() {
        return reviewTimeStamp;
    }

    public void setReviewTimeStamp(String reviewTimeStamp) {
        this.reviewTimeStamp = reviewTimeStamp;
    }

    public ReviewerFeedback(String id, String reviewerUserId, String reviewStatus, String reviewFeedback, String reviewTimeStamp) {
        this.id = id;
        this.reviewerUserId = reviewerUserId;
        this.reviewStatus = reviewStatus;
        this.reviewFeedback = reviewFeedback;
        this.reviewTimeStamp = reviewTimeStamp;
    }

    public ReviewerFeedback() {
    }

    @Override
    public String toString() {
        return "ReviewerFeedback{" +
                "id='" + id + '\'' +
                ", reviewerUserId='" + reviewerUserId + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", reviewFeedback='" + reviewFeedback + '\'' +
                ", reviewTimeStamp='" + reviewTimeStamp + '\'' +
                '}';
    }
}
