package com.api.automation.workflow.examples;

import com.api.automation.workflow.*;
import java.util.List;

/**
 * Advanced example demonstrating complete workflow management
 */
public class AdvancedWorkflowManagementExample {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Workflow Management Example ===\n");
        
        WorkflowManager manager = WorkflowManager.getInstance();
        manager.clear();
        
        // Setup listeners
        manager.addListener(new DefaultWorkflowListener());
        manager.addListener(new PerformanceMonitoringListener());
        
        // Register sample workflows
        registerWorkflows(manager);
        
        // Display initial state
        System.out.println("\n" + manager.getSummary());
        
        // Interactive workflow management
        demonstrateEnableDisable(manager);
        demonstrateExecution(manager);
        demonstratePriority(manager);
        demonstrateHistory(manager);
    }
    
    private static void registerWorkflows(WorkflowManager manager) {
        System.out.println("Registering workflows...\n");
        
        // Register multiple workflows
        manager.registerWorkflow(new WorkflowConfig(
            new SampleWorkflow("wf-setup", "Setup Workflow", "Initialize test environment"),
            true,
            5
        ));
        
        manager.registerWorkflow(new WorkflowConfig(
            new SampleWorkflow("wf-data-prep", "Data Preparation", "Prepare test data"),
            true,
            4
        ));
        
        manager.registerWorkflow(new WorkflowConfig(
            new SampleWorkflow("wf-tests", "Run Tests", "Execute test suite"),
            true,
            3
        ));
        
        manager.registerWorkflow(new WorkflowConfig(
            new SampleWorkflow("wf-cleanup", "Cleanup", "Clean up resources"),
            false,
            2
        ));
        
        manager.registerWorkflow(new WorkflowConfig(
            new SampleWorkflow("wf-report", "Generate Report", "Create test report"),
            false,
            1
        ));
    }
    
    private static void demonstrateEnableDisable(WorkflowManager manager) {
        System.out.println("\n=== Enable/Disable Management ===\n");
        
        System.out.println("Current state:");
        System.out.println("  wf-cleanup enabled: " + manager.isWorkflowEnabled("wf-cleanup"));
        System.out.println("  wf-report enabled: " + manager.isWorkflowEnabled("wf-report"));
        
        System.out.println("\nEnabling wf-cleanup...");
        manager.enableWorkflow("wf-cleanup");
        System.out.println("  wf-cleanup enabled: " + manager.isWorkflowEnabled("wf-cleanup"));
        
        System.out.println("\nDisabling wf-tests...");
        manager.disableWorkflow("wf-tests");
        System.out.println("  wf-tests enabled: " + manager.isWorkflowEnabled("wf-tests"));
        
        System.out.println("\nWorkflow counts:");
        System.out.println("  Total: " + manager.getWorkflowCount());
        System.out.println("  Enabled: " + manager.getEnabledWorkflowCount());
        System.out.println("  Disabled: " + (manager.getWorkflowCount() - manager.getEnabledWorkflowCount()));
    }
    
    private static void demonstrateExecution(WorkflowManager manager) {
        System.out.println("\n=== Workflow Execution ===\n");
        
        System.out.println("Executing all enabled workflows in priority order...\n");
        List<WorkflowExecutionResult> results = manager.executeAllEnabledWorkflows();
        
        System.out.println("\nExecution Summary:");
        System.out.println("Total executed: " + results.size());
        long successCount = results.stream().filter(WorkflowExecutionResult::isSuccess).count();
        System.out.println("Successful: " + successCount);
        System.out.println("Failed: " + (results.size() - successCount));
    }
    
    private static void demonstratePriority(WorkflowManager manager) {
        System.out.println("\n=== Priority-Based Execution ===\n");
        
        List<WorkflowConfig> enabledWorkflows = manager.getEnabledWorkflows();
        System.out.println("Enabled workflows (ordered by priority - highest first):\n");
        
        for (int i = 0; i < enabledWorkflows.size(); i++) {
            WorkflowConfig config = enabledWorkflows.get(i);
            System.out.printf("%d. %s (ID: %s, Priority: %d)\n",
                i + 1,
                config.getWorkflowName(),
                config.getWorkflowId(),
                config.getPriority()
            );
        }
    }
    
    private static void demonstrateHistory(WorkflowManager manager) {
        System.out.println("\n=== Execution History ===\n");
        
        List<WorkflowExecutionResult> history = manager.getExecutionHistory();
        System.out.println("Total executions: " + history.size() + "\n");
        
        if (!history.isEmpty()) {
            System.out.println("Recent executions:");
            history.stream()
                .skip(Math.max(0, history.size() - 5))
                .forEach(result -> {
                    System.out.println("  - " + result.getWorkflowName() +
                                     ": " + (result.isSuccess() ? "SUCCESS" : "FAILED") +
                                     " (" + result.getExecutionDurationMs() + "ms)");
                });
        }
        
        // Get history for specific workflow
        System.out.println("\nExecution history for 'wf-setup':");
        List<WorkflowExecutionResult> setupHistory = 
            manager.getExecutionHistoryForWorkflow("wf-setup");
        System.out.println("  Total executions: " + setupHistory.size());
    }
    
    /**
     * Custom listener for performance monitoring
     */
    private static class PerformanceMonitoringListener implements WorkflowListener {
        
        @Override
        public void onWorkflowRegistered(WorkflowConfig config) {
            // Not needed for this example
        }
        
        @Override
        public void onWorkflowUnregistered(WorkflowConfig config) {
            // Not needed for this example
        }
        
        @Override
        public void onWorkflowEnabled(WorkflowConfig config) {
            // Not needed for this example
        }
        
        @Override
        public void onWorkflowDisabled(WorkflowConfig config) {
            // Not needed for this example
        }
        
        @Override
        public void onWorkflowStarted(WorkflowConfig config) {
            System.out.println("[MONITOR] Workflow started: " + config.getWorkflowName());
        }
        
        @Override
        public void onWorkflowExecuted(WorkflowExecutionResult result) {
            if (result.getExecutionDurationMs() > 200) {
                System.out.println("[MONITOR] SLOW EXECUTION: " + result.getWorkflowName() + 
                                 " took " + result.getExecutionDurationMs() + "ms");
            }
        }
    }
}
