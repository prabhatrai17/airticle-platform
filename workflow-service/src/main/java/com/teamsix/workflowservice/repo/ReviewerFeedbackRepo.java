package com.teamsix.workflowservice.repo;

import com.teamsix.workflowservice.entity.ReviewerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerFeedbackRepo extends JpaRepository<ReviewerFeedback,String> {
}
