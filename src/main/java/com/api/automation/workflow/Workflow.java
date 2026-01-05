package com.api.automation.workflow;

/**
 * Interface for defining workflow contracts
 */
public interface Workflow {
    
    /**
     * Get the unique identifier for this workflow
     * @return workflow ID
     */
    String getId();
    
    /**
     * Get the name of the workflow
     * @return workflow name
     */
    String getName();
    
    /**
     * Execute the workflow
     * @return true if execution was successful, false otherwise
     */
    boolean execute();
    
    /**
     * Validate if the workflow can be executed
     * @return true if workflow is valid, false otherwise
     */
    boolean validate();
    
    /**
     * Get the description of the workflow
     * @return workflow description
     */
    String getDescription();
}
