package com.teamsix.workflowservice.controller;

import com.teamsix.workflowservice.entity.*;
import com.teamsix.workflowservice.openFeign.ArticleFeign;
import com.teamsix.workflowservice.payload.ArticleDto;
import com.teamsix.workflowservice.repo.WorkflowRepo;
import com.teamsix.workflowservice.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    @Autowired
    WorkflowService workFlowService;
    @Autowired
    ArticleFeign articleFeign;
    @GetMapping("/hello/{articleId}")
    public String hello(@PathVariable String articleId){


//    articleId    List<Article> articleres= articleFeign.getArticleById(articleId).getBody();
//        if(Objects.nonNull(articleres)){
//            System.out.println("from article service");
//            System.out.println(articleres);
//        }
        return "hello from workflow";
    }

    @PostMapping("/workflow")
    public WorkFlow saveWorkflow(@RequestBody WorkFlow workFlow){
        System.out.println(workFlow);
        return workFlowService.saveWorkFlow(workFlow);
    }
    @GetMapping("/workflow")
    public List<WorkFlow> getWorkflows(){
        return workFlowService.getAllWorkFlow();
    }
    @GetMapping("/workflow/{workflowId}")
    public WorkFlow getWorkflowById(@PathVariable String workflowId){
        return workFlowService.getWorkFlowById(workflowId);
    }
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getWorkflowByArticleId(@PathVariable String articleId){
        WorkFlow workFlowRes=workFlowService.getWorkFlowByArticleId(articleId);
        if(workFlowRes!=null) return new ResponseEntity<>(workFlowRes,HttpStatus.FOUND);
        return new ResponseEntity<>("not found, something went wrong",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/assign/editor/{articleId}/{editorId}")
    public ResponseEntity<?> assignArticleToEditor(@PathVariable String articleId, @PathVariable String editorId){
       WorkFlow workFlow= workFlowService.assignEditorToArticle(articleId,editorId);
       if(workFlow==null) return new ResponseEntity<>("could not assign editor to article", HttpStatus.BAD_REQUEST);
       else return new ResponseEntity<>(workFlow,HttpStatus.OK);
    }

    @GetMapping("/assign/reviewer/{articleId}/{reviewerId}")
    public ResponseEntity<?> assignArticleToReviewer(@PathVariable String articleId,@PathVariable String reviewerId){
        WorkFlow workFlow= workFlowService.assignArticleToReviewer(articleId,reviewerId);
        if(workFlow==null) return new ResponseEntity<>("could not assign entity", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(workFlow,HttpStatus.OK);
    }

    @PostMapping("/feedback/reviewer/{articleId}")
    public ResponseEntity<?> reviewerFeedbackToArticle(@RequestBody ReviewerFeedback reviewerFeedback, @PathVariable String articleId){

        WorkFlow workFlow= workFlowService.giveFeedbackToArticle(reviewerFeedback,articleId);
        if(workFlow==null) return new ResponseEntity<>("could not add feedback", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(workFlow,HttpStatus.OK);
    }
    @PostMapping("/changes/editor/{articleId}")
    public ResponseEntity<?> saveBriefChangesByEditorToArticle(@RequestBody EditorChanges editorChanges, @PathVariable String articleId){
        System.out.println(editorChanges);
        WorkFlow workFlow= workFlowService.editorChangesToArticle(editorChanges,articleId);
        if(workFlow==null) return new ResponseEntity<>("could not add feedback", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(workFlow,HttpStatus.OK);
    }

    @PostMapping("/edit/editor/{articleId}/{editorId}")
    public ResponseEntity<?> editorEditArticleAndSaveEditorChanges(@RequestBody ArticleDto articleDto, @PathVariable String articleId,@PathVariable String editorId){
        System.out.println(articleDto);
         WorkFlow workFlow= workFlowService.editorEditArticleAndSaveEditorChanges(articleDto,articleId,editorId);
       if(workFlow==null) return new ResponseEntity<>("could not edit, something went wrong", HttpStatus.BAD_REQUEST);
       else return new ResponseEntity<>(workFlow,HttpStatus.OK);

    }
    @GetMapping("editor/workflow/review/status/{status}/{articleId}")
    public ResponseEntity<?> changeReviewerReviewStatus(@PathVariable String status, @PathVariable String articleId){
        System.out.println(status);
        WorkFlow res= workFlowService.changeWorkflowReviewStatus(status,articleId);
        if(res==null) new ResponseEntity<>("could not update review status", HttpStatus.BAD_REQUEST);
          return  new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/editor/workflow/editor/comment/{comment}/{articleId}/{editorChangeId}")
    public ResponseEntity<?> addCommentsToEditorChanges(@PathVariable String comment, @PathVariable String articleId,@PathVariable String editorChangeId){
        System.out.println(comment);
        WorkFlow res= workFlowService.addCommentsOnEditorChanges(comment,articleId,editorChangeId);
        if(res==null) new ResponseEntity<>("could not add comment on editor changes", HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>(res,HttpStatus.OK);
    }


}
