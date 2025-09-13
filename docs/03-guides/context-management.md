# KMIP Context Management

This guide explains how to properly use `KmipContext` for managing KMIP specification versions and thread-local state in your application.

## Overview

`KmipContext` is a thread-local utility that manages the current KMIP specification version for the calling thread. It ensures that operations use the correct KMIP version without requiring version parameters to be passed through your entire call stack.

## Basic Usage

### Setting the KMIP Version

```java
// Set the KMIP spec for the current thread
KmipContext.setSpec(KmipSpec.V1_4);

try {
    // Perform operations that depend on the KMIP spec...
    State state = new State(State.Standard.ACTIVE);
    // ... more operations ...
} catch (IllegalArgumentException e) {
    // Handle version compatibility or other validation errors
    throw new IllegalStateException("Failed to perform operation with KMIP " + KmipContext.getSpec(), e);
} finally {
    // Always clear the context when finished
    KmipContext.clear();
}
```

### Getting the Current Spec

```java
// Get the current KMIP spec for the current thread
KmipSpec currentSpec = KmipContext.getSpec();

// Check if a spec is set
if (currentSpec != null) {
    // Safe to perform version-dependent operations
    if (currentSpec == KmipSpec.V1_4) {
        // Use features available in 1.4
    } else {
        // Fallback for other versions
    }
} else {
    throw new IllegalStateException("KMIP spec not set in current context");
}
```

## Best Practices

### 1. Always Use Try-Finally

Always use a try-finally block to ensure the context is cleared, even if an exception occurs:

```java
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // Your code here
} finally {
    KmipContext.clear();
}
```

### 2. Minimize Context Scope

Keep the context scope as small as possible to avoid leaking the version to other operations:

```java
// ❌ Avoid:
KmipContext.setSpec(KmipSpec.V1_4);
// ... many lines of code ...
KmipContext.clear();

// ✅ Better:
{
    KmipContext.setSpec(KmipSpec.V1_4);
    try {
        // Only the code that needs this version
        return processWithVersion();
    } finally {
        KmipContext.clear();
    }
}
```

### 3. Context in Multi-threaded Environments

When working with thread pools or executors, ensure the context is properly transferred:

```java
// Capture the current spec before creating the task
KmipSpec currentSpec = KmipContext.getSpec();

// Create a thread-safe task with context
Runnable task = () -> {
    if (currentSpec == null) {
        throw new IllegalStateException("No KMIP spec available for async task");
    }
    
    KmipContext.setSpec(currentSpec);
    try {
        // Task implementation
        State state = new State(State.Standard.ACTIVE);
        // ... rest of the task ...
    } catch (Exception e) {
        // Log and handle exceptions appropriately
        Thread.currentThread().getUncaughtExceptionHandler()
            .uncaughtException(Thread.currentThread(), e);
        throw e;
    } finally {
        KmipContext.clear();
    }
};

// Submit task with proper error handling
CompletableFuture.runAsync(task, executor)
    .exceptionally(ex -> {
        // Handle any uncaught exceptions
        System.err.println("Task failed: " + ex.getMessage());
        return null;
    });
```

## Common Patterns

### Version-Aware Operations

```java
public <T> T executeWithVersion(KmipSpec spec, Supplier<T> operation) {
    KmipContext.setSpec(spec);
    try {
        return operation.get();
    } finally {
        KmipContext.clear();
    }
}

// Usage:
String result = executeWithVersion(KmipSpec.V1_4, () -> {
    // Your version-dependent code here
    return "Operation completed with " + KmipContext.getSpec();
});
```

### Fallback Version Handling

```java
public <T> T tryWithFallbackVersions(Supplier<T> operation, KmipSpec... fallbackSpecs) {
    if (operation == null) {
        throw new IllegalArgumentException("Operation cannot be null");
    }
    if (fallbackSpecs == null) {
        throw new IllegalArgumentException("Fallback specs cannot be null");
    }

    // Try with current spec first if available
    KmipSpec currentSpec = KmipContext.getSpec();
    if (currentSpec != null) {
        try {
            return operation.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Continue to fallbacks if version-specific operation failed
            if (fallbackSpecs.length == 0) {
                throw new IllegalStateException("Operation failed with current spec and no fallbacks provided", e);
            }
            // Continue to fallbacks
        }
    }
    
    // Try each fallback spec
    for (KmipSpec spec : fallbackSpecs) {
        if (spec == null) {
            continue; // Skip null specs in the fallback list
        }
        
        KmipContext.setSpec(spec);
        try {
            return operation.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Try next fallback
            continue;
        } finally {
            KmipContext.clear();
        }
    }
    
    throw new IllegalStateException("Operation failed with all fallback versions");
}
```

## Troubleshooting

### Common Issues

1. **Context Not Set**
   - **Symptom**: `NullPointerException` or incorrect version behavior
   - **Solution**: Ensure `KmipContext.setSpec()` is called before operations that depend on it

2. **Context Not Cleared**
   - **Symptom**: Version "leaks" to other operations
   - **Solution**: Always use try-finally to clear the context

3. **Thread Pool Issues**
   - **Symptom**: Incorrect version in async operations
   - **Solution**: Explicitly transfer the context to worker threads

## Best Practices Summary

1. Always clear the context in a finally block
2. Keep the context scope minimal
3. Be explicit about version requirements in method contracts
4. Document when methods depend on the context
5. Consider using wrapper methods for common context patterns
