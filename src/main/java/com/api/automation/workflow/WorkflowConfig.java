package com.api.automation.workflow;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration wrapper for a workflow including enabled state and metadata
 */
@Getter
@Setter
public class WorkflowConfig {
    private final Workflow workflow;
    private boolean enabled;
    private int priority;
    private Map<String, Object> metadata;
    private WorkflowStatus status;
    
    public WorkflowConfig(Workflow workflow) {
        this(workflow, true, 0);
    }
    
    public WorkflowConfig(Workflow workflow, boolean enabled, int priority) {
        this.workflow = workflow;
        this.enabled = enabled;
        this.priority = priority;
        this.metadata = new HashMap<>();
        this.status = WorkflowStatus.PENDING;
    }
    
    public Object getMetadataValue(String key) {
        return metadata.get(key);
    }
    
    public void setMetadataValue(String key, Object value) {
        metadata.put(key, value);
    }
    
    public String getWorkflowId() {
        return workflow.getId();
    }
    
    public String getWorkflowName() {
        return workflow.getName();
    }
}
