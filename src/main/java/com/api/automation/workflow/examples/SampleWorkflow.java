package com.api.automation.workflow.examples;

import com.api.automation.workflow.AbstractWorkflow;

/**
 * Example workflow implementation
 */
public class SampleWorkflow extends AbstractWorkflow {
    
    public SampleWorkflow(String id, String name, String description) {
        super(id, name, description);
    }
    
    @Override
    public boolean validate() {
        System.out.println("Validating workflow: " + name);
        return true;
    }
    
    @Override
    public boolean execute() {
        try {
            System.out.println("Executing workflow: " + name);
            Thread.sleep(100); // Simulate work
            System.out.println("Workflow completed: " + name);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
