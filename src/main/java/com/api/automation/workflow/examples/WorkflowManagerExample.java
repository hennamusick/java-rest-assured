package com.api.automation.workflow.examples;

import com.api.automation.workflow.*;

/**
 * Example usage of WorkflowManager
 */
public class WorkflowManagerExample {
    
    public static void main(String[] args) {
        WorkflowManager manager = WorkflowManager.getInstance();
        
        // Add listener for logging
        manager.addListener(new DefaultWorkflowListener());
        
        // Create and register workflows
        Workflow workflow1 = new SampleWorkflow("wf-001", "User Registration", 
                "Workflow for user registration process");
        Workflow workflow2 = new SampleWorkflow("wf-002", "Payment Processing", 
                "Workflow for processing payments");
        Workflow workflow3 = new SampleWorkflow("wf-003", "Data Validation", 
                "Workflow for validating incoming data");
        
        // Register workflows with different priorities
        WorkflowConfig config1 = new WorkflowConfig(workflow1, true, 3);
        WorkflowConfig config2 = new WorkflowConfig(workflow2, true, 2);
        WorkflowConfig config3 = new WorkflowConfig(workflow3, false, 1);
        
        manager.registerWorkflow(config1);
        manager.registerWorkflow(config2);
        manager.registerWorkflow(config3);
        
        System.out.println("\n=== Initial State ===");
        System.out.println(manager.getSummary());
        
        // Enable a previously disabled workflow
        System.out.println("\n=== Enabling workflow wf-003 ===");
        manager.enableWorkflow("wf-003");
        
        // Disable a workflow
        System.out.println("\n=== Disabling workflow wf-002 ===");
        manager.disableWorkflow("wf-002");
        
        System.out.println("\n=== State After Changes ===");
        System.out.println(manager.getSummary());
        
        // Execute all enabled workflows
        System.out.println("\n=== Executing All Enabled Workflows ===");
        manager.executeAllEnabledWorkflows();
        
        // Get execution history
        System.out.println("\n=== Execution History ===");
        for (WorkflowExecutionResult result : manager.getExecutionHistory()) {
            System.out.println(result);
        }
        
        // Check individual workflow status
        System.out.println("\n=== Individual Workflow Status ===");
        System.out.println("wf-001 status: " + manager.getWorkflowStatus("wf-001"));
        System.out.println("wf-002 status: " + manager.getWorkflowStatus("wf-002"));
        System.out.println("wf-003 status: " + manager.getWorkflowStatus("wf-003"));
    }
}
