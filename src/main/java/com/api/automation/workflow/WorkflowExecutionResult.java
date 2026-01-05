package com.api.automation.workflow;

import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Result of workflow execution
 */
@Getter
public class WorkflowExecutionResult {
    private final String workflowId;
    private final String workflowName;
    private final boolean success;
    private final WorkflowStatus status;
    private final LocalDateTime executionTime;
    private final long executionDurationMs;
    private final String errorMessage;
    
    public WorkflowExecutionResult(String workflowId, String workflowName, boolean success, 
                                   WorkflowStatus status, LocalDateTime executionTime, 
                                   long executionDurationMs, String errorMessage) {
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.success = success;
        this.status = status;
        this.executionTime = executionTime;
        this.executionDurationMs = executionDurationMs;
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString() {
        return "WorkflowExecutionResult{" +
                "workflowId='" + workflowId + '\'' +
                ", workflowName='" + workflowName + '\'' +
                ", success=" + success +
                ", status=" + status +
                ", executionTime=" + executionTime +
                ", executionDurationMs=" + executionDurationMs +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
