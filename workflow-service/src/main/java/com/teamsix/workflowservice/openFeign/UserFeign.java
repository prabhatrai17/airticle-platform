package com.teamsix.workflowservice.openFeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE",path = "/api/user")
public interface UserFeign {
}
