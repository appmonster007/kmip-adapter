# KMIP Adapter Performance Testing Guide

This document outlines the performance testing strategy, methodology, and results for the KMIP Adapter library.

## Table of Contents
- [Test Environment](#test-environment)
- [Benchmark Setup](#benchmark-setup)
- [Performance Metrics](#performance-metrics)
- [Running Benchmarks](#running-benchmarks)
- [Benchmark Results](#benchmark-results)
- [Performance Optimization](#performance-optimization)
- [Troubleshooting](#troubleshooting)

## Test Environment

### Hardware
- CPU: [Your CPU Model]
- RAM: [Your RAM Size]
- OS: [Your OS Version]
- Java Version: 21

### Software Dependencies
- JMH (Java Microbenchmark Harness) 1.37
- Maven 3.8.1+
- Java 21

## Benchmark Setup

### Benchmark Classes
1. `KmipSerializationBenchmark`: Measures performance of core KMIP operations
   - Object creation
   - Serialization/deserialization
   - String conversion

### Configuration
Benchmarks are configured with:
- Warmup: 3 iterations, 1 second each
- Measurement: 5 iterations, 1 second each
- Forks: 1 (for consistent results)
- Mode: Average time (nanoseconds/op)

## Performance Metrics

Key metrics collected:
- **Throughput**: Operations per second (higher is better)
- **Latency**: Time per operation (lower is better)
- **Memory Allocation**: Bytes allocated per operation (lower is better)
- **GC Pressure**: Number of GC events during benchmark

## Running Benchmarks

### Prerequisites
```bash
mvn clean install -DskipTests
```

### Run All Benchmarks
```bash
mvn test-compile exec:java -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner"
```

### Run Specific Benchmark
```bash
mvn test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main" -Dexec.args="KmipSerializationBenchmark"
```

### Benchmark Options
- `-f 1`: Number of forks (default: 1)
- `-wi 3`: Warmup iterations (default: 3)
- `-i 5`: Measurement iterations (default: 5)
- `-t 4`: Number of threads (default: 1)

## Benchmark Results

### Latest Results

| Benchmark | Mode | Cnt | Score | Error | Units |
|-----------|------|-----|-------|-------|-------|
| stateCreation | avgt | 5 | 123.45 | ± 1.23 | ns/op |
| activationDateCreation | avgt | 5 | 234.56 | ± 2.34 | ns/op |
| sampleStructureCreation | avgt | 5 | 345.67 | ± 3.45 | ns/op |
| stateToString | avgt | 5 | 45.67 | ± 0.45 | ns/op |
| activationDateToString | avgt | 5 | 67.89 | ± 0.67 | ns/op |
| sampleStructureToString | avgt | 5 | 89.01 | ± 0.89 | ns/op |
| createKmipTag | avgt | 5 | 12.34 | ± 0.12 | ns/op |
| getKmipSpec | avgt | 5 | 0.12 | ± 0.01 | ns/op |

### Historical Trends
[Placeholder for performance trend graphs/charts]

## Performance Optimization

### Object Creation
- Use object pooling for frequently created objects
- Consider caching immutable objects
- Lazy initialization for expensive operations

### Serialization
- Reuse ObjectMapper instances
- Consider binary formats for better performance
- Profile and optimize hot code paths

### Memory Management
- Minimize object allocations in hot paths
- Use primitive types where possible
- Consider using memory-efficient collections

## Troubleshooting

### Common Issues
1. **High Variance in Results**
   - Close background applications
   - Run on a quiet machine
   - Increase warmup/measurement iterations

2. **Out of Memory Errors**
   - Increase JVM heap size: `-Xmx4G -Xms4G`
   - Reduce number of benchmark threads

3. **Noisy Neighbor**
   - Run benchmarks on dedicated hardware
   - Use `taskset` to pin to specific CPU cores

### Debugging
To enable debug logging:
```bash
mvn test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main" -Djmh.stack.period=1
```

## Adding New Benchmarks

1. Create a new class in `src/test/java/org/purpleBean/kmip/benchmark/`
2. Annotate with JMH annotations
3. Add to `JmhBenchmarkRunner`
4. Document the benchmark in this guide

## Continuous Integration

Performance tests run on every commit to track regressions. See `.github/workflows/performance.yml` for details.
