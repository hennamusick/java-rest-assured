package com.api.automation.workflow;

import lombok.Getter;

/**
 * Abstract base class for implementing workflows
 */
@Getter
public abstract class AbstractWorkflow implements Workflow {
    protected String id;
    protected String name;
    protected String description;
    
    public AbstractWorkflow(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public boolean validate() {
        return true;
    }
    
    @Override
    public abstract boolean execute();
}
