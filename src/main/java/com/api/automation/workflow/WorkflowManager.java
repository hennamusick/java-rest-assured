package com.api.automation.workflow;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Manages multiple workflows with enable/disable functionality
 * Provides centralized control for workflow registration, execution, and status tracking
 */
public class WorkflowManager {
    private static final WorkflowManager INSTANCE = new WorkflowManager();
    
    @Getter
    private final Map<String, WorkflowConfig> workflows;
    @Getter
    private final List<WorkflowExecutionResult> executionHistory;
    @Getter
    private final List<WorkflowListener> listeners;
    private volatile boolean running;
    
    private WorkflowManager() {
        this.workflows = new LinkedHashMap<>();
        this.executionHistory = new CopyOnWriteArrayList<>();
        this.listeners = new CopyOnWriteArrayList<>();
        this.running = false;
    }
    
    /**
     * Get singleton instance of WorkflowManager
     * @return WorkflowManager instance
     */
    public static WorkflowManager getInstance() {
        return INSTANCE;
    }
    
    /**
     * Register a new workflow
     * @param workflow the workflow to register
     */
    public void registerWorkflow(Workflow workflow) {
        registerWorkflow(new WorkflowConfig(workflow));
    }
    
    /**
     * Register a new workflow with configuration
     * @param workflowConfig the workflow configuration
     */
    public void registerWorkflow(WorkflowConfig workflowConfig) {
        if (workflows.containsKey(workflowConfig.getWorkflowId())) {
            throw new IllegalArgumentException("Workflow with ID '" + workflowConfig.getWorkflowId() + 
                    "' is already registered");
        }
        workflows.put(workflowConfig.getWorkflowId(), workflowConfig);
        notifyWorkflowRegistered(workflowConfig);
    }
    
    /**
     * Unregister a workflow
     * @param workflowId the ID of the workflow to unregister
     */
    public void unregisterWorkflow(String workflowId) {
        WorkflowConfig removed = workflows.remove(workflowId);
        if (removed != null) {
            notifyWorkflowUnregistered(removed);
        }
    }
    
    /**
     * Enable a workflow
     * @param workflowId the ID of the workflow to enable
     */
    public void enableWorkflow(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        if (config == null) {
            throw new IllegalArgumentException("Workflow with ID '" + workflowId + "' not found");
        }
        config.setEnabled(true);
        notifyWorkflowEnabled(config);
    }
    
    /**
     * Disable a workflow
     * @param workflowId the ID of the workflow to disable
     */
    public void disableWorkflow(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        if (config == null) {
            throw new IllegalArgumentException("Workflow with ID '" + workflowId + "' not found");
        }
        config.setEnabled(false);
        notifyWorkflowDisabled(config);
    }
    
    /**
     * Check if a workflow is enabled
     * @param workflowId the ID of the workflow
     * @return true if enabled, false otherwise
     */
    public boolean isWorkflowEnabled(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        return config != null && config.isEnabled();
    }
    
    /**
     * Get a workflow by ID
     * @param workflowId the ID of the workflow
     * @return the workflow, or null if not found
     */
    public Workflow getWorkflow(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        return config != null ? config.getWorkflow() : null;
    }
    
    /**
     * Get workflow configuration by ID
     * @param workflowId the ID of the workflow
     * @return the workflow configuration, or null if not found
     */
    public WorkflowConfig getWorkflowConfig(String workflowId) {
        return workflows.get(workflowId);
    }
    
    /**
     * Get all registered workflows
     * @return list of all workflows
     */
    public List<WorkflowConfig> getAllWorkflows() {
        return new ArrayList<>(workflows.values());
    }
    
    /**
     * Get all enabled workflows
     * @return list of enabled workflows sorted by priority
     */
    public List<WorkflowConfig> getEnabledWorkflows() {
        return workflows.values().stream()
                .filter(WorkflowConfig::isEnabled)
                .sorted(Comparator.comparingInt(WorkflowConfig::getPriority).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Get all disabled workflows
     * @return list of disabled workflows
     */
    public List<WorkflowConfig> getDisabledWorkflows() {
        return workflows.values().stream()
                .filter(config -> !config.isEnabled())
                .collect(Collectors.toList());
    }
    
    /**
     * Execute all enabled workflows in priority order
     * @return list of execution results
     */
    public List<WorkflowExecutionResult> executeAllEnabledWorkflows() {
        List<WorkflowExecutionResult> results = new ArrayList<>();
        List<WorkflowConfig> enabledWorkflows = getEnabledWorkflows();
        
        for (WorkflowConfig config : enabledWorkflows) {
            WorkflowExecutionResult result = executeWorkflow(config.getWorkflowId());
            results.add(result);
        }
        
        return results;
    }
    
    /**
     * Execute a specific workflow
     * @param workflowId the ID of the workflow to execute
     * @return the execution result
     */
    public WorkflowExecutionResult executeWorkflow(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        if (config == null) {
            throw new IllegalArgumentException("Workflow with ID '" + workflowId + "' not found");
        }
        
        if (!config.isEnabled()) {
            WorkflowExecutionResult result = new WorkflowExecutionResult(
                    config.getWorkflowId(),
                    config.getWorkflowName(),
                    false,
                    WorkflowStatus.SKIPPED,
                    LocalDateTime.now(),
                    0,
                    "Workflow is disabled"
            );
            executionHistory.add(result);
            notifyWorkflowExecuted(result);
            return result;
        }
        
        long startTime = System.currentTimeMillis();
        config.setStatus(WorkflowStatus.RUNNING);
        notifyWorkflowStarted(config);
        
        try {
            if (!config.getWorkflow().validate()) {
                WorkflowExecutionResult result = new WorkflowExecutionResult(
                        config.getWorkflowId(),
                        config.getWorkflowName(),
                        false,
                        WorkflowStatus.FAILED,
                        LocalDateTime.now(),
                        System.currentTimeMillis() - startTime,
                        "Workflow validation failed"
                );
                config.setStatus(result.getStatus());
                executionHistory.add(result);
                notifyWorkflowExecuted(result);
                return result;
            }
            
            boolean executionSuccess = config.getWorkflow().execute();
            long duration = System.currentTimeMillis() - startTime;
            
            WorkflowStatus status = executionSuccess ? WorkflowStatus.COMPLETED : WorkflowStatus.FAILED;
            WorkflowExecutionResult result = new WorkflowExecutionResult(
                    config.getWorkflowId(),
                    config.getWorkflowName(),
                    executionSuccess,
                    status,
                    LocalDateTime.now(),
                    duration,
                    executionSuccess ? null : "Workflow execution returned false"
            );
            
            config.setStatus(status);
            executionHistory.add(result);
            notifyWorkflowExecuted(result);
            
            return result;
            
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            WorkflowExecutionResult result = new WorkflowExecutionResult(
                    config.getWorkflowId(),
                    config.getWorkflowName(),
                    false,
                    WorkflowStatus.FAILED,
                    LocalDateTime.now(),
                    duration,
                    "Workflow execution error: " + e.getMessage()
            );
            config.setStatus(result.getStatus());
            executionHistory.add(result);
            notifyWorkflowExecuted(result);
            return result;
        }
    }
    
    /**
     * Get execution history
     * @return list of all execution results
     */
    public List<WorkflowExecutionResult> getExecutionHistoryForWorkflow(String workflowId) {
        return executionHistory.stream()
                .filter(result -> result.getWorkflowId().equals(workflowId))
                .collect(Collectors.toList());
    }
    
    /**
     * Clear execution history
     */
    public void clearExecutionHistory() {
        executionHistory.clear();
    }
    
    /**
     * Get the status of a workflow
     * @param workflowId the ID of the workflow
     * @return the workflow status, or null if not found
     */
    public WorkflowStatus getWorkflowStatus(String workflowId) {
        WorkflowConfig config = workflows.get(workflowId);
        return config != null ? config.getStatus() : null;
    }
    
    /**
     * Add a workflow listener
     * @param listener the listener to add
     */
    public void addListener(WorkflowListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Remove a workflow listener
     * @param listener the listener to remove
     */
    public void removeListener(WorkflowListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * Get total number of registered workflows
     * @return count of registered workflows
     */
    public int getWorkflowCount() {
        return workflows.size();
    }
    
    /**
     * Get count of enabled workflows
     * @return count of enabled workflows
     */
    public int getEnabledWorkflowCount() {
        return (int) workflows.values().stream()
                .filter(WorkflowConfig::isEnabled)
                .count();
    }
    
    /**
     * Clear all workflows
     */
    public void clear() {
        workflows.clear();
        executionHistory.clear();
    }
    
    /**
     * Get workflow summary
     * @return string summary of all workflows
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Workflow Manager Summary ===\n");
        sb.append("Total Workflows: ").append(getWorkflowCount()).append("\n");
        sb.append("Enabled Workflows: ").append(getEnabledWorkflowCount()).append("\n");
        sb.append("Disabled Workflows: ").append(workflows.size() - getEnabledWorkflowCount()).append("\n");
        sb.append("\nWorkflows:\n");
        
        for (WorkflowConfig config : workflows.values()) {
            sb.append("  - [").append(config.isEnabled() ? "ENABLED" : "DISABLED").append("] ");
            sb.append(config.getWorkflowName()).append(" (ID: ").append(config.getWorkflowId());
            sb.append(", Priority: ").append(config.getPriority()).append(", Status: ");
            sb.append(config.getStatus().getDisplayName()).append(")\n");
        }
        
        return sb.toString();
    }
    
    // Listener notification methods
    private void notifyWorkflowRegistered(WorkflowConfig config) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowRegistered(config);
        }
    }
    
    private void notifyWorkflowUnregistered(WorkflowConfig config) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowUnregistered(config);
        }
    }
    
    private void notifyWorkflowEnabled(WorkflowConfig config) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowEnabled(config);
        }
    }
    
    private void notifyWorkflowDisabled(WorkflowConfig config) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowDisabled(config);
        }
    }
    
    private void notifyWorkflowStarted(WorkflowConfig config) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowStarted(config);
        }
    }
    
    private void notifyWorkflowExecuted(WorkflowExecutionResult result) {
        for (WorkflowListener listener : listeners) {
            listener.onWorkflowExecuted(result);
        }
    }
}
