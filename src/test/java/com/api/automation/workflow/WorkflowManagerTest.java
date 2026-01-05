package com.api.automation.workflow;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Unit tests for WorkflowManager
 */
public class WorkflowManagerTest {
    private WorkflowManager manager;
    private TestWorkflow testWorkflow1;
    private TestWorkflow testWorkflow2;
    
    @BeforeMethod
    public void setUp() {
        manager = WorkflowManager.getInstance();
        manager.clear();
        
        testWorkflow1 = new TestWorkflow("test-1", "Test Workflow 1", "Test workflow for unit testing");
        testWorkflow2 = new TestWorkflow("test-2", "Test Workflow 2", "Another test workflow");
    }
    
    @Test
    public void testRegisterWorkflow() {
        manager.registerWorkflow(testWorkflow1);
        
        assertEquals(1, manager.getWorkflowCount());
        assertNotNull(manager.getWorkflow("test-1"));
        assertEquals("Test Workflow 1", manager.getWorkflow("test-1").getName());
    }
    
    @Test
    public void testRegisterDuplicateWorkflow() {
        manager.registerWorkflow(testWorkflow1);
        
        expectThrows(IllegalArgumentException.class, () -> {
            manager.registerWorkflow(testWorkflow1);
        });
    }
    
    @Test
    public void testUnregisterWorkflow() {
        manager.registerWorkflow(testWorkflow1);
        manager.unregisterWorkflow("test-1");
        
        assertEquals(0, manager.getWorkflowCount());
        assertNull(manager.getWorkflow("test-1"));
    }
    
    @Test
    public void testEnableDisableWorkflow() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, false, 0));
        
        assertFalse(manager.isWorkflowEnabled("test-1"));
        
        manager.enableWorkflow("test-1");
        assertTrue(manager.isWorkflowEnabled("test-1"));
        
        manager.disableWorkflow("test-1");
        assertFalse(manager.isWorkflowEnabled("test-1"));
    }
    
    @Test
    public void testGetEnabledWorkflows() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, true, 2));
        manager.registerWorkflow(new WorkflowConfig(testWorkflow2, false, 1));
        
        List<WorkflowConfig> enabled = manager.getEnabledWorkflows();
        assertEquals(1, enabled.size());
        assertEquals("test-1", enabled.get(0).getWorkflowId());
    }
    
    @Test
    public void testGetDisabledWorkflows() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, true, 2));
        manager.registerWorkflow(new WorkflowConfig(testWorkflow2, false, 1));
        
        List<WorkflowConfig> disabled = manager.getDisabledWorkflows();
        assertEquals(1, disabled.size());
        assertEquals("test-2", disabled.get(0).getWorkflowId());
    }
    
    @Test
    public void testExecuteWorkflow() {
        manager.registerWorkflow(testWorkflow1);
        
        WorkflowExecutionResult result = manager.executeWorkflow("test-1");
        
        assertTrue(result.isSuccess());
        assertEquals(WorkflowStatus.COMPLETED, result.getStatus());
        assertEquals("test-1", result.getWorkflowId());
    }
    
    @Test
    public void testExecuteDisabledWorkflow() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, false, 0));
        
        WorkflowExecutionResult result = manager.executeWorkflow("test-1");
        
        assertFalse(result.isSuccess());
        assertEquals(WorkflowStatus.SKIPPED, result.getStatus());
    }
    
    @Test
    public void testExecuteAllEnabledWorkflows() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, true, 2));
        manager.registerWorkflow(new WorkflowConfig(testWorkflow2, true, 1));
        
        List<WorkflowExecutionResult> results = manager.executeAllEnabledWorkflows();
        
        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(WorkflowExecutionResult::isSuccess));
    }
    
    @Test
    public void testExecutionHistory() {
        manager.registerWorkflow(testWorkflow1);
        manager.executeWorkflow("test-1");
        manager.executeWorkflow("test-1");
        
        List<WorkflowExecutionResult> history = manager.getExecutionHistory();
        assertEquals(2, history.size());
    }
    
    @Test
    public void testGetExecutionHistoryForWorkflow() {
        manager.registerWorkflow(testWorkflow1);
        manager.registerWorkflow(testWorkflow2);
        manager.executeWorkflow("test-1");
        manager.executeWorkflow("test-1");
        manager.executeWorkflow("test-2");
        
        List<WorkflowExecutionResult> history = manager.getExecutionHistoryForWorkflow("test-1");
        assertEquals(2, history.size());
        assertTrue(history.stream().allMatch(r -> r.getWorkflowId().equals("test-1")));
    }
    
    @Test
    public void testWorkflowListener() {
        TestWorkflowListener listener = new TestWorkflowListener();
        manager.addListener(listener);
        
        manager.registerWorkflow(testWorkflow1);
        assertTrue(listener.isWorkflowRegisteredCalled());
        
        manager.enableWorkflow("test-1");
        assertTrue(listener.isWorkflowEnabledCalled());
        
        manager.disableWorkflow("test-1");
        assertTrue(listener.isWorkflowDisabledCalled());
    }
    
    @Test
    public void testWorkflowPriority() {
        manager.registerWorkflow(new WorkflowConfig(testWorkflow1, true, 1));
        manager.registerWorkflow(new WorkflowConfig(testWorkflow2, true, 5));
        
        List<WorkflowConfig> enabledByPriority = manager.getEnabledWorkflows();
        assertEquals(2, enabledByPriority.size());
        assertEquals("test-2", enabledByPriority.get(0).getWorkflowId()); // Higher priority first
        assertEquals("test-1", enabledByPriority.get(1).getWorkflowId());
    }
    
    /**
     * Test implementation of Workflow
     */
    private static class TestWorkflow extends AbstractWorkflow {
        public TestWorkflow(String id, String name, String description) {
            super(id, name, description);
        }
        
        @Override
        public boolean execute() {
            return true;
        }
    }
    
    /**
     * Test implementation of WorkflowListener
     */
    private static class TestWorkflowListener implements WorkflowListener {
        private boolean workflowRegisteredCalled;
        private boolean workflowEnabledCalled;
        private boolean workflowDisabledCalled;
        
        @Override
        public void onWorkflowRegistered(WorkflowConfig config) {
            workflowRegisteredCalled = true;
        }
        
        @Override
        public void onWorkflowUnregistered(WorkflowConfig config) {}
        
        @Override
        public void onWorkflowEnabled(WorkflowConfig config) {
            workflowEnabledCalled = true;
        }
        
        @Override
        public void onWorkflowDisabled(WorkflowConfig config) {
            workflowDisabledCalled = true;
        }
        
        @Override
        public void onWorkflowStarted(WorkflowConfig config) {}
        
        @Override
        public void onWorkflowExecuted(WorkflowExecutionResult result) {}
        
        public boolean isWorkflowRegisteredCalled() {
            return workflowRegisteredCalled;
        }
        
        public boolean isWorkflowEnabledCalled() {
            return workflowEnabledCalled;
        }
        
        public boolean isWorkflowDisabledCalled() {
            return workflowDisabledCalled;
        }
    }
}
