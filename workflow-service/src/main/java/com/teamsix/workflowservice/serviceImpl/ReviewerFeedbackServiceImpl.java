package com.teamsix.workflowservice.serviceImpl;

import com.teamsix.workflowservice.entity.ReviewerFeedback;
import com.teamsix.workflowservice.repo.ReviewerFeedbackRepo;
import com.teamsix.workflowservice.service.ReviewerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewerFeedbackServiceImpl implements ReviewerFeedbackService {
    @Autowired
    ReviewerFeedbackRepo reviewerFeedbackRepo;


    @Override
    public ReviewerFeedback saveReviewFeedback(ReviewerFeedback reviewerFeedback) {
        return reviewerFeedbackRepo.save(reviewerFeedback);
    }
}
