package com.teamsix.workflowservice.repo;

import com.teamsix.workflowservice.entity.EditorChanges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorChangesRepo extends JpaRepository<EditorChanges,String> {
}
