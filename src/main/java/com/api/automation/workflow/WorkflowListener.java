package com.api.automation.workflow;

/**
 * Listener interface for workflow events
 */
public interface WorkflowListener {
    
    /**
     * Called when a workflow is registered
     * @param config the workflow configuration
     */
    void onWorkflowRegistered(WorkflowConfig config);
    
    /**
     * Called when a workflow is unregistered
     * @param config the workflow configuration
     */
    void onWorkflowUnregistered(WorkflowConfig config);
    
    /**
     * Called when a workflow is enabled
     * @param config the workflow configuration
     */
    void onWorkflowEnabled(WorkflowConfig config);
    
    /**
     * Called when a workflow is disabled
     * @param config the workflow configuration
     */
    void onWorkflowDisabled(WorkflowConfig config);
    
    /**
     * Called when a workflow starts execution
     * @param config the workflow configuration
     */
    void onWorkflowStarted(WorkflowConfig config);
    
    /**
     * Called when a workflow completes execution
     * @param result the execution result
     */
    void onWorkflowExecuted(WorkflowExecutionResult result);
}
