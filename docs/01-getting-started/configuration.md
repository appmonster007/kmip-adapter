# Configuration Guide

## Table of Contents
- [Basic Setup](#basic-setup)
- [Thread Safety](#thread-safety)
- [Spring Boot Integration](#spring-boot-integration)
- [Custom Serialization](#custom-serialization)
- [Performance Tuning](#performance-tuning)
- [Logging Configuration](#logging-configuration)
- [Common Configurations](#common-configurations)

## Basic Setup

The KMIP Adapter requires minimal configuration. By default, it uses the latest KMIP specification version (1.2).

```java
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;

// Set the KMIP spec version for the current thread
KmipContext.setSpec(KmipSpec.V1_2);

// Get the current KMIP spec
KmipSpec currentSpec = KmipContext.getSpec();
```

## Thread Safety

`KmipContext` uses `ThreadLocal` to store the current specification, making it thread-safe by default. However, you need to manage the context lifecycle properly:

### Basic Usage

```java
try {
    // Set the KMIP spec for the current thread
    KmipContext.setSpec(KmipSpec.V1_2);
    
    // Your KMIP operations here
    // The context is automatically available to all KMIP operations in this thread
    
} finally {
    // Always clear the context when done to prevent memory leaks
    KmipContext.clear();
}
```

### Using with Thread Pools

When using thread pools, you need to propagate the context:

```java
// Get the current context
KmipSpec currentSpec = KmipContext.getSpec();

executorService.submit(() -> {
    try {
        // Set the context in the new thread
        KmipContext.setSpec(currentSpec);
        
        // Your KMIP operations here
        
    } finally {
        KmipContext.clear();
    }
});
```

## Spring Boot Integration

For Spring Boot applications, you can configure the KMIP adapter using a `@Configuration` class:

### Basic Configuration

```java
import org.purpleBean.kmip.KmipSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KmipConfig {
    
    @Bean
    public KmipSpec kmipSpec() {
        return KmipSpec.V1_2; // or load from configuration
    }
    
    @Bean
    public KmipContext kmipContext(KmipSpec spec) {
        return new KmipContext(spec);
    }
}
```
