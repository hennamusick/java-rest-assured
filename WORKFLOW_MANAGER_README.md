# Workflow Manager Documentation

## Overview

The Workflow Manager is a centralized component designed to manage multiple workflows with enable/disable functionality. It provides complete lifecycle management, execution control, and event-driven notifications for workflows.

## Architecture

### Core Components

1. **Workflow** - Interface defining workflow contracts
2. **WorkflowManager** - Singleton manager for controlling multiple workflows
3. **WorkflowConfig** - Configuration wrapper for workflows with metadata
4. **WorkflowStatus** - Enum representing workflow execution states
5. **WorkflowExecutionResult** - Result object capturing execution details
6. **WorkflowListener** - Event listener interface for workflow events
7. **AbstractWorkflow** - Base class for implementing workflows

## Key Features

### 1. Workflow Registration
- Register workflows with unique IDs
- Support for duplicate registration prevention
- Configure workflows with priority and metadata

### 2. Enable/Disable Management
```java
// Enable a workflow
manager.enableWorkflow("workflow-id");

// Disable a workflow
manager.disableWorkflow("workflow-id");

// Check if enabled
boolean enabled = manager.isWorkflowEnabled("workflow-id");
```

### 3. Execution Control
- Execute individual workflows
- Execute all enabled workflows in priority order
- Automatic validation before execution
- Skip disabled workflows automatically

### 4. Status Tracking
- Track workflow status: PENDING, RUNNING, COMPLETED, FAILED, SKIPPED
- Maintain execution history with timestamps and duration
- Query status for individual workflows

### 5. Event Notifications
- Workflow registration/unregistration events
- Enable/disable events
- Execution start/completion events
- Custom listener implementations

### 6. Priority-Based Execution
Workflows can be assigned priorities (higher values execute first):
```java
WorkflowConfig config = new WorkflowConfig(workflow, true, 5);
manager.registerWorkflow(config);
```

## Usage Examples

### Basic Usage

```java
// Get singleton instance
WorkflowManager manager = WorkflowManager.getInstance();

// Create a workflow
Workflow myWorkflow = new MyWorkflow("wf-001", "My Workflow", "Description");

// Register the workflow
manager.registerWorkflow(myWorkflow);

// Execute the workflow
WorkflowExecutionResult result = manager.executeWorkflow("wf-001");

// Check result
if (result.isSuccess()) {
    System.out.println("Workflow executed successfully");
    System.out.println("Duration: " + result.getExecutionDurationMs() + "ms");
}
```

### Creating Custom Workflows

Extend `AbstractWorkflow` to create custom workflows:

```java
public class UserRegistrationWorkflow extends AbstractWorkflow {
    
    public UserRegistrationWorkflow() {
        super("user-reg", "User Registration", 
              "Handles user registration workflow");
    }
    
    @Override
    public boolean validate() {
        // Perform validation
        return true;
    }
    
    @Override
    public boolean execute() {
        try {
            // Implement workflow logic
            System.out.println("Registering user...");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

### Advanced Configuration

```java
// Create workflow with custom config
WorkflowConfig config = new WorkflowConfig(
    myWorkflow,
    true,  // enabled
    5      // priority
);

// Add metadata
config.setMetadataValue("retry_count", 3);
config.setMetadataValue("timeout_ms", 30000);

// Register
manager.registerWorkflow(config);
```

### Event Listeners

Implement `WorkflowListener` for event notifications:

```java
public class MyWorkflowListener implements WorkflowListener {
    
    @Override
    public void onWorkflowEnabled(WorkflowConfig config) {
        System.out.println("Workflow enabled: " + config.getWorkflowName());
    }
    
    @Override
    public void onWorkflowExecuted(WorkflowExecutionResult result) {
        if (!result.isSuccess()) {
            System.out.println("Error: " + result.getErrorMessage());
        }
    }
    
    // Implement other methods...
}

// Register listener
manager.addListener(new MyWorkflowListener());
```

### Batch Execution

```java
// Execute all enabled workflows in priority order
List<WorkflowExecutionResult> results = manager.executeAllEnabledWorkflows();

for (WorkflowExecutionResult result : results) {
    System.out.println(result.getWorkflowName() + ": " + 
                       (result.isSuccess() ? "SUCCESS" : "FAILED"));
}
```

### Querying Workflows

```java
// Get all workflows
List<WorkflowConfig> allWorkflows = manager.getAllWorkflows();

// Get enabled workflows (sorted by priority)
List<WorkflowConfig> enabledWorkflows = manager.getEnabledWorkflows();

// Get disabled workflows
List<WorkflowConfig> disabledWorkflows = manager.getDisabledWorkflows();

// Get execution history
List<WorkflowExecutionResult> history = manager.getExecutionHistory();

// Get history for specific workflow
List<WorkflowExecutionResult> workflowHistory = 
    manager.getExecutionHistoryForWorkflow("wf-001");
```

### Status Management

```java
// Get workflow status
WorkflowStatus status = manager.getWorkflowStatus("wf-001");
System.out.println("Status: " + status.getDisplayName());

// Get summary
System.out.println(manager.getSummary());

// Get counts
int totalWorkflows = manager.getWorkflowCount();
int enabledCount = manager.getEnabledWorkflowCount();
```

## API Reference

### WorkflowManager

#### Registration Methods
- `void registerWorkflow(Workflow workflow)` - Register a workflow
- `void registerWorkflow(WorkflowConfig config)` - Register with config
- `void unregisterWorkflow(String workflowId)` - Unregister a workflow

#### Enable/Disable Methods
- `void enableWorkflow(String workflowId)` - Enable a workflow
- `void disableWorkflow(String workflowId)` - Disable a workflow
- `boolean isWorkflowEnabled(String workflowId)` - Check if enabled

#### Query Methods
- `Workflow getWorkflow(String workflowId)` - Get workflow
- `WorkflowConfig getWorkflowConfig(String workflowId)` - Get config
- `List<WorkflowConfig> getAllWorkflows()` - Get all workflows
- `List<WorkflowConfig> getEnabledWorkflows()` - Get enabled workflows
- `List<WorkflowConfig> getDisabledWorkflows()` - Get disabled workflows
- `WorkflowStatus getWorkflowStatus(String workflowId)` - Get status

#### Execution Methods
- `WorkflowExecutionResult executeWorkflow(String workflowId)` - Execute single
- `List<WorkflowExecutionResult> executeAllEnabledWorkflows()` - Execute all

#### History Methods
- `List<WorkflowExecutionResult> getExecutionHistory()` - Get all history
- `List<WorkflowExecutionResult> getExecutionHistoryForWorkflow(String workflowId)` - Get specific
- `void clearExecutionHistory()` - Clear history

#### Listener Methods
- `void addListener(WorkflowListener listener)` - Add listener
- `void removeListener(WorkflowListener listener)` - Remove listener

#### Utility Methods
- `int getWorkflowCount()` - Get total count
- `int getEnabledWorkflowCount()` - Get enabled count
- `String getSummary()` - Get summary string
- `void clear()` - Clear all workflows and history

## Workflow Lifecycle

1. **Registration** - Workflow registered with manager
2. **Configuration** - Set enabled state, priority, and metadata
3. **Validation** - Workflow validates before execution
4. **Execution** - Workflow executes with result capture
5. **Completion** - Result stored in history

## Status Transitions

```
PENDING → RUNNING → COMPLETED
       ↓         ↓
       └─ SKIPPED (if disabled)
       
       → RUNNING → FAILED (on error/validation failure)
```

## Thread Safety

The WorkflowManager uses thread-safe collections (`LinkedHashMap` and `CopyOnWriteArrayList`) for concurrent access. The manager is safe to use from multiple threads.

## Best Practices

1. **Use Singleton Pattern** - WorkflowManager is a singleton
2. **Register Early** - Register all workflows at application startup
3. **Set Priorities** - Configure workflow priorities for correct execution order
4. **Add Listeners** - Use listeners for monitoring and logging
5. **Check Results** - Always check execution results for success/failure
6. **Handle Exceptions** - Implement proper error handling in workflows
7. **Clean Up** - Clear history periodically to manage memory

## Example: Complete Integration

```java
public class WorkflowApplication {
    
    public static void main(String[] args) {
        // Initialize manager
        WorkflowManager manager = WorkflowManager.getInstance();
        
        // Add default listener
        manager.addListener(new DefaultWorkflowListener());
        
        // Create and register workflows
        registerWorkflows(manager);
        
        // Print initial state
        System.out.println(manager.getSummary());
        
        // Execute workflows
        manager.executeAllEnabledWorkflows();
        
        // Print results
        printResults(manager);
    }
    
    private static void registerWorkflows(WorkflowManager manager) {
        manager.registerWorkflow(
            new WorkflowConfig(
                new UserRegistrationWorkflow(),
                true,
                3
            )
        );
        
        manager.registerWorkflow(
            new WorkflowConfig(
                new PaymentProcessingWorkflow(),
                true,
                2
            )
        );
        
        manager.registerWorkflow(
            new WorkflowConfig(
                new NotificationWorkflow(),
                false,
                1
            )
        );
    }
    
    private static void printResults(WorkflowManager manager) {
        System.out.println("\n=== Execution Results ===");
        for (WorkflowExecutionResult result : manager.getExecutionHistory()) {
            System.out.println(result);
        }
    }
}
```

## Testing

Use the provided `WorkflowManagerTest` class as reference for testing:

```java
@Test
public void testWorkflowExecution() {
    WorkflowManager manager = WorkflowManager.getInstance();
    manager.registerWorkflow(new TestWorkflow("test-1", "Test", "Test workflow"));
    
    WorkflowExecutionResult result = manager.executeWorkflow("test-1");
    
    assertTrue(result.isSuccess());
    assertEquals(WorkflowStatus.COMPLETED, result.getStatus());
}
```

## Package Structure

```
com.api.automation.workflow/
├── Workflow.java                    # Workflow interface
├── WorkflowManager.java             # Main manager class
├── WorkflowConfig.java              # Configuration wrapper
├── WorkflowStatus.java              # Status enum
├── WorkflowExecutionResult.java     # Execution result
├── WorkflowListener.java            # Event listener interface
├── AbstractWorkflow.java            # Base workflow class
├── DefaultWorkflowListener.java     # Default listener implementation
├── examples/
│   ├── SampleWorkflow.java          # Example workflow
│   └── WorkflowManagerExample.java  # Usage example
```

## Future Enhancements

- Workflow dependencies and ordering
- Async/parallel execution support
- Retry policies for failed workflows
- Workflow scheduling
- Persistence layer for workflow state
- Dashboard for monitoring workflows
