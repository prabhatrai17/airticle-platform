package com.teamsix.workflowservice.repo;

import com.teamsix.workflowservice.entity.WorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkflowRepo extends JpaRepository<WorkFlow, String> {
    @Query(value = "select * from work_flow wf where wf.article_id=?1", nativeQuery = true)
    public WorkFlow findByArticleId(String articleId);
}
