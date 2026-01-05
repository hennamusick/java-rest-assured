package com.api.automation.workflow;

import lombok.Getter;

/**
 * Enum to represent workflow execution status
 */
@Getter
public enum WorkflowStatus {
    PENDING("Pending"),
    RUNNING("Running"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    SKIPPED("Skipped");
    
    private final String displayName;
    
    WorkflowStatus(String displayName) {
        this.displayName = displayName;
    }
}
