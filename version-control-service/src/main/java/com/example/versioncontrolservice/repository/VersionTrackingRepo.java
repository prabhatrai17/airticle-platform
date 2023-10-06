package com.example.versioncontrolservice.repository;

import com.example.versioncontrolservice.entity.VersionTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionTrackingRepo extends JpaRepository<VersionTracking, Integer> {
}
