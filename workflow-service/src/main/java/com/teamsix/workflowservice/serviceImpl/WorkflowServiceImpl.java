package com.teamsix.workflowservice.serviceImpl;

import com.teamsix.workflowservice.customException.GlobalException;
import com.teamsix.workflowservice.entity.EditorChanges;
import com.teamsix.workflowservice.entity.ReviewerFeedback;
import com.teamsix.workflowservice.entity.WorkFlow;
import com.teamsix.workflowservice.openFeign.ArticleFeign;
import com.teamsix.workflowservice.payload.ArticleDto;
import com.teamsix.workflowservice.repo.EditorChangesRepo;
import com.teamsix.workflowservice.repo.ReviewerFeedbackRepo;
import com.teamsix.workflowservice.repo.WorkflowRepo;
import com.teamsix.workflowservice.service.WorkflowService;
import com.teamsix.workflowservice.utils.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    private static final Logger logger= LoggerUtil.getLogger();
    @Autowired
    WorkflowRepo workflowRepo;
    @Autowired
    ReviewerFeedbackRepo reviewerFeedbackRepo;

//    @Autowired
//    ArticleRepo articleRepo;

    @Autowired
    EditorChangesRepo editorChangesRepo;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ArticleFeign articleFeign;


    @Override
    public WorkFlow saveWorkFlow(WorkFlow workFlow) {

//
//        WorkFlow workFlowRes=this.getWorkFlowByArticleId(workFlow.getArticleId());
//        if(workFlowRes!=null) throw new WorkFlowAlreadyExist("workflow status already exist for provided article id");
//        ReviewerFeedback reviewerFeedback=workFlow.getReviewerFeedback();
//        if(workFlow.getReviewerFeedback()!=null || workFlow.getReviewerFeedback().getId()=="")
//            reviewerFeedbackRepo.save(reviewerFeedback);
//        return workflowRepo.save(workFlow);
        return null;
    }

    @Override
    public WorkFlow getWorkFlowById(String workFlowId) {

        return workflowRepo.findById(workFlowId).get();

    }

    @Override
    public List<WorkFlow> getAllWorkFlow() {
        return workflowRepo.findAll();
    }

    public WorkFlow getWorkFlowByArticleId(String articleId) {
        WorkFlow wfRes = workflowRepo.findByArticleId(articleId);
        if (Objects.isNull(wfRes)) throw new GlobalException("could not find workflow, kindly check article id");
        return wfRes;
    }

    @Override
    public WorkFlow changeWorkflowReviewStatus(String status, String articleId) {
        WorkFlow workFlowres = this.getWorkFlowByArticleId(articleId);
        if (status == "" || status == null) throw new GlobalException("review status empty or null");
        workFlowres.getReviewerFeedback().setReviewStatus(status);
        return workflowRepo.save(workFlowres);
    }

    @Override
    public WorkFlow addCommentsOnEditorChanges(String comment, String articleId, String editorChangeId) {
        WorkFlow workFlowres = this.getWorkFlowByArticleId(articleId);
        System.out.println(workFlowres);
        if (comment == "" || comment == null) throw new GlobalException("comment is empty or null");
        List<EditorChanges> l = workFlowres.getEditorChangesList();

        for (EditorChanges ec : l) {
//            System.out.println(ec.getEditorUserId());
            System.out.println(ec.getId());

            //System.out.println(ec.getEditorUserId().equals(editorChangeId));
            System.out.println(ec.getId().equals(editorChangeId));
            if (ec.getId().equals(editorChangeId)) {
                System.out.println("inside if");
                ec.setAuthorComment(comment);

                break;
            }


        }
        System.out.println(workFlowres);

        return workflowRepo.save(workFlowres);

    }

    @Override
    public WorkFlow UpdateWorkFlow(WorkFlow workFlow) {
        return null;
    }

    // @CircuitBreaker(name = "assignEditorToArticle",fallbackMethod = "handleAssignEditorToArticle")
    @Override
    public WorkFlow assignEditorToArticle(String articleId, String editorId) {
        //ARTICLE SERVICE CALL TO GET ARTICLE OBJECT
        List<ArticleDto> articleList = articleFeign.getArticleById(articleId).getBody();
        WorkFlow workFlow;
        if (Objects.nonNull(articleList)) {
            System.out.println(articleList.size());
//            for (ArticleDto a : articleList) {
//                System.out.println(a);
//            }
            ArticleDto articleLatestVersion = articleList.get(articleList.size() - 1);
            System.out.println(articleLatestVersion);
            System.out.println("from article service");
            //System.out.println(articleList.get(articleList.size() - 1));

            ReviewerFeedback reviewerFeedback = new ReviewerFeedback();
            reviewerFeedback.setReviewStatus("pending");
            reviewerFeedbackRepo.save(reviewerFeedback);
            List<EditorChanges> editorChangesList = new ArrayList<>();
            workFlow = new WorkFlow("", articleLatestVersion, editorId, "", "", "", "", reviewerFeedback, editorChangesList);
            System.out.println(workFlow);
            workFlow = workflowRepo.save(workFlow);
        } else throw new GlobalException("bad request, article not found");

        return workFlow;
    }

//    private String handleAssignEditorToArticle() {
//        System.out.println("list of your article");
//return "list of your article";
//    }

    @Override
    public WorkFlow assignArticleToReviewer(String articleId, String reviewerId) {

        WorkFlow workFlow = this.getWorkFlowByArticleId(articleId);
        System.out.println(workFlow);
//        if (Objects.isNull(workFlow))
//            throw new NoSuchElementException("article doesn't exist or editor not yet assigned or something went wrong");
        workFlow.setReviewerUserId(reviewerId);
        workFlow = workflowRepo.save(workFlow);
        System.out.println((workFlow));
        if (Objects.isNull(workFlow)) throw new GlobalException("couldn't assign review to your article, try again");
        else
            return workFlow;
    }

    @Override
    public WorkFlow giveFeedbackToArticle(ReviewerFeedback reviewerFeedback, String articleId) {
        WorkFlow workFlow = this.getWorkFlowByArticleId(articleId);
        if (workFlow == null) throw new NoSuchElementException("Article doesn't exist, kindly verify id");

        ReviewerFeedback reviewerFeedbackFinal = workFlow.getReviewerFeedback();
        reviewerFeedbackFinal.setReviewerUserId(workFlow.getReviewerUserId());
        reviewerFeedbackFinal.setReviewStatus("in-progress");
        reviewerFeedbackFinal.setReviewFeedback(reviewerFeedback.getReviewFeedback());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        reviewerFeedbackFinal.setReviewTimeStamp(now.format(df).toString());

        workFlow.setReviewerFeedback(reviewerFeedbackFinal);
        workflowRepo.save(workFlow);
        return workFlow;
    }

    @Override
    public WorkFlow editorChangesToArticle(EditorChanges editorChanges, String articleId) {
        WorkFlow workFlow = this.getWorkFlowByArticleId(articleId);
        if (workFlow == null) throw new NoSuchElementException("Article doesn't exist, kindly verify id");
        //ReviewerFeedback reviewerFeedbackFinal=workFlow.getReviewerFeedback();
        EditorChanges editorChangesFinal = new EditorChanges();
        editorChangesFinal.setEditorUserId(workFlow.getEditorUserId());
        editorChangesFinal.setChangedDescription(editorChanges.getChangedDescription());
        editorChangesFinal.setAuthorComment("");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        editorChangesFinal.setChangedTimeStamp(now.format(df).toString());

        EditorChanges editorChangesRes = editorChangesRepo.save(editorChangesFinal);
        workFlow.getEditorChangesList().add(editorChangesRes);
        workflowRepo.save(workFlow);
        return workFlow;
    }

    @Override
    public WorkFlow editorEditArticleAndSaveEditorChanges(ArticleDto articleDto, String articleId, String editorId) {
        List<ArticleDto> articleDtoList=articleFeign.getArticleById(articleId).getBody();
        if(Objects.isNull(articleDtoList) || articleDtoList.isEmpty()) throw new GlobalException("article not found for id");
        ArticleDto articleDtoPrevious=articleDtoList.get(articleDtoList.size()-1);
        logger.info("article previous version");
        logger.info(articleDtoPrevious.getTitle());
        logger.info(articleDtoPrevious.getContent());

        ArticleDto articleDtoLatest=articleFeign.createVersion(articleId,articleDto).getBody();
        logger.info("new created version of article");
        logger.info(articleDtoLatest.getTitle());
        logger.info(articleDtoLatest.getContent());

   //     String changedValue="";
     String[] prevArr =articleDtoPrevious.getContent().split(" ");
      String[] curArr =articleDtoLatest.getContent().split(" ");
      for(String s:prevArr) logger.info(s);
        for(String s1:curArr) logger.info(s1);

      logger.info("prev arr: "+prevArr.length);
        logger.info("curr arr: "+curArr.length);

        EditorChanges editorChanges= new EditorChanges();
        editorChanges.setEditorUserId(editorId);
        int prevArticleLength=prevArr.length;
        int currArticleLength=curArr.length;
        if(currArticleLength>prevArticleLength)
            editorChanges.setChangedDescription("article words increased by: "+(currArticleLength-prevArticleLength));
        else
            editorChanges.setChangedDescription("article words decreased by: "+(prevArticleLength-currArticleLength));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        editorChanges.setChangedTimeStamp(now.format(df).toString());

         WorkFlow workflow= this.getWorkFlowByArticleId(articleId);
         logger.info("workflow before change "+workflow);


         editorChangesRepo.save(editorChanges);
         workflow.getEditorChangesList().add(editorChanges);
         workflow=workflowRepo.save(workflow);
         logger.info("workflow after change "+workflow);

   return workflow;

    }
}
