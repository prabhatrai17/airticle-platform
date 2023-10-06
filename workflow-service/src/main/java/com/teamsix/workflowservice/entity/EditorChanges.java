package com.teamsix.workflowservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class EditorChanges {
    @Id
    //@GeneratedValue

    @UuidGenerator
    String id;
    String editorUserId;

    String changedTimeStamp;
    String changedDescription;
    String AuthorComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditorUserId() {
        return editorUserId;
    }

    public void setEditorUserId(String editorUserId) {
        this.editorUserId = editorUserId;
    }

    public String getChangedTimeStamp() {
        return changedTimeStamp;
    }

    public void setChangedTimeStamp(String changedTimeStamp) {
        this.changedTimeStamp = changedTimeStamp;
    }

    public String getChangedDescription() {
        return changedDescription;
    }

    public void setChangedDescription(String changedDescription) {
        this.changedDescription = changedDescription;
    }

    public String getAuthorComment() {
        return AuthorComment;
    }

    public void setAuthorComment(String authorComment) {
        AuthorComment = authorComment;
    }

    public EditorChanges() {
    }

    public EditorChanges(String id, String editorUserId, String changedTimeStamp, String changedDescription, String authorComment) {
        this.id = id;
        this.editorUserId = editorUserId;
        this.changedTimeStamp = changedTimeStamp;
        this.changedDescription = changedDescription;
        AuthorComment = authorComment;
    }

    @Override
    public String toString() {
        return "EditorChanges{" +
                "id='" + id + '\'' +
                ", editorUserId='" + editorUserId + '\'' +
                ", changedTimeStamp='" + changedTimeStamp + '\'' +
                ", changedDescription='" + changedDescription + '\'' +
                ", AuthorComment='" + AuthorComment + '\'' +
                '}';
    }
}
