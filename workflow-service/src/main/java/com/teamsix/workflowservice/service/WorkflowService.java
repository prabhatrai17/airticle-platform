package com.teamsix.workflowservice.service;

import com.teamsix.workflowservice.entity.EditorChanges;
import com.teamsix.workflowservice.entity.ReviewerFeedback;
import com.teamsix.workflowservice.entity.WorkFlow;
import com.teamsix.workflowservice.payload.ArticleDto;

import java.util.List;

public interface WorkflowService {
    WorkFlow saveWorkFlow(WorkFlow workFlow);
    WorkFlow getWorkFlowById(String workFlowId);

    List<WorkFlow> getAllWorkFlow();
    WorkFlow UpdateWorkFlow(WorkFlow workFlow);
    WorkFlow assignEditorToArticle(String articleId, String editorId);
    WorkFlow assignArticleToReviewer(String articleId,String reviewerId);


    WorkFlow giveFeedbackToArticle(ReviewerFeedback reviewerFeedback, String articleId);

    WorkFlow editorChangesToArticle(EditorChanges editorChanges, String articleId);

    WorkFlow editorEditArticleAndSaveEditorChanges(ArticleDto articleDto, String articleId, String editorId);

    WorkFlow getWorkFlowByArticleId(String articleId);

    WorkFlow changeWorkflowReviewStatus(String status, String articleId);

    WorkFlow addCommentsOnEditorChanges(String comment, String articleId, String editorChangeId);


}
