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
- JMH (Java Microbenchmark Harness) 1.36
- Maven 3.6+
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

Benchmarks are wired into Maven profiles so they don't affect normal unit test runs.

### Run All Benchmarks (standard)
```bash
mvn -Pperf verify
```

### Quick Benchmarks (reduced warmups/iterations)
```bash
mvn -Pperf-fast verify
```

### Customize JMH Options
Pass JMH arguments via `bench.args`:
```bash
mvn -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify
```

Common flags:
- `-f 1`: number of forks
- `-wi 3`: warmup iterations
- `-i 5`: measurement iterations
- `-t 4`: number of threads

## Benchmark Results

### Latest Results (2025-09-13)

#### Throughput (Operations per Microsecond)
| Benchmark | Mode | Cnt | Score | Error | Units |
|-----------|------|-----|-------|-------|-------|
| createKmipTag | thrpt | 3 | 725,471 | ± 593,746 | ops/us |
| getKmipSpec | thrpt | 3 | 1,002,952 | ± 21,393 | ops/us |
| stateCreation | thrpt | 3 | 159,661 | ± 8,019 | ops/us |
| sampleStructureCreation | thrpt | 3 | 36,207 | ± 1,655 | ops/us |
| stateToString | thrpt | 3 | 2.263 | ± 0.087 | ops/us |
| sampleStructureToString | thrpt | 3 | 0.741 | ± 0.060 | ops/us |
| activationDateCreation | thrpt | 3 | 85.354 | ± 19.307 | ops/us |
| activationDateToString | thrpt | 3 | 2.183 | ± 1.914 | ops/us |

#### Average Time per Operation (Microseconds)
| Benchmark | Mode | Cnt | Score | Error | Units |
|-----------|------|-----|-------|-------|-------|
| getKmipSpec | avgt | 3 | 0.001 | ± 0.001 | us/op |
| createKmipTag | avgt | 3 | 0.001 | ± 0.001 | us/op |
| stateCreation | avgt | 3 | 0.006 | ± 0.006 | us/op |
| sampleStructureCreation | avgt | 3 | 0.028 | ± 0.026 | us/op |
| stateToString | avgt | 3 | 0.445 | ± 0.408 | us/op |
| activationDateToString | avgt | 3 | 0.447 | ± 0.007 | us/op |
| sampleStructureToString | avgt | 3 | 1.355 | ± 1.217 | us/op |

### Performance Analysis

1. **Fastest Operations** (sub-microsecond):
   - KMIP spec lookup and tag creation are the fastest operations
   - State and sample structure creation are in the tens of nanoseconds

2. **Moderate Operations** (hundreds of nanoseconds):
   - String conversions for states and activation dates
   
3. **Most Expensive Operations** (microseconds):
   - Sample structure to string conversion is the most expensive operation

### Recommendations
- Consider optimizing string conversion logic for sample structures
- Object pooling could benefit operations with higher allocation rates
- The high variance in some results suggests potential for JIT warmup optimization

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
