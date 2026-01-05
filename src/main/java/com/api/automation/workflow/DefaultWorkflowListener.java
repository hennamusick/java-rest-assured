package com.api.automation.workflow;

import java.util.logging.Logger;

/**
 * Default implementation of WorkflowListener with logging
 */
public class DefaultWorkflowListener implements WorkflowListener {
    private static final Logger LOGGER = Logger.getLogger(DefaultWorkflowListener.class.getName());
    
    @Override
    public void onWorkflowRegistered(WorkflowConfig config) {
        LOGGER.info("Workflow registered: " + config.getWorkflowName() + " (ID: " + config.getWorkflowId() + ")");
    }
    
    @Override
    public void onWorkflowUnregistered(WorkflowConfig config) {
        LOGGER.info("Workflow unregistered: " + config.getWorkflowName() + " (ID: " + config.getWorkflowId() + ")");
    }
    
    @Override
    public void onWorkflowEnabled(WorkflowConfig config) {
        LOGGER.info("Workflow enabled: " + config.getWorkflowName() + " (ID: " + config.getWorkflowId() + ")");
    }
    
    @Override
    public void onWorkflowDisabled(WorkflowConfig config) {
        LOGGER.info("Workflow disabled: " + config.getWorkflowName() + " (ID: " + config.getWorkflowId() + ")");
    }
    
    @Override
    public void onWorkflowStarted(WorkflowConfig config) {
        LOGGER.info("Workflow started: " + config.getWorkflowName() + " (ID: " + config.getWorkflowId() + ")");
    }
    
    @Override
    public void onWorkflowExecuted(WorkflowExecutionResult result) {
        String status = result.isSuccess() ? "SUCCESS" : "FAILED";
        LOGGER.info("Workflow executed - Name: " + result.getWorkflowName() + 
                   ", Status: " + status + 
                   ", Duration: " + result.getExecutionDurationMs() + "ms");
        if (!result.isSuccess() && result.getErrorMessage() != null) {
            LOGGER.warning("Error: " + result.getErrorMessage());
        }
    }
}
