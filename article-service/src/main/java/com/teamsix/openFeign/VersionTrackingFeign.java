package com.teamsix.openFeign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("VERSION-TRACKING-SERVICE")
public interface VersionTrackingFeign {
	
	

}
