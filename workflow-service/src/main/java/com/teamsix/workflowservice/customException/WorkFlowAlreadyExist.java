package com.teamsix.workflowservice.customException;

public class WorkFlowAlreadyExist extends RuntimeException {
    public WorkFlowAlreadyExist(String msg){
        super(msg);
    }

}
