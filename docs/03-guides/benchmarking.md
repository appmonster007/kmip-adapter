# Benchmarking Guide

## Overview

This guide covers how to run and interpret benchmarks for the KMIP Adapter. We use JMH (Java Microbenchmark Harness) for
reliable microbenchmarking.

## Running Benchmarks

### Prerequisites

- Java 21+
- Maven 3.6+

### Running All Benchmarks (standard)

```bash
mvn -Pperf verify
```

### Quick Benchmarks (reduced warmups/iterations)

```bash
mvn -Pperf-fast verify
```

### Running Specific Benchmarks / Custom Options

Pass JMH options via `bench.args`:

```bash
# Throughput mode, JSON report, specific benchmark selection
mvn -Pperf -Dbench.args="KmipSerializationBenchmark -bm thrpt -rf json -rff target/jmh.json" verify
```

### Running a Single Benchmark Subject

You can now select one or more benchmark subjects without changing code using the `bench.subject` system property. If
not provided, all discovered subjects are benchmarked.

```bash
# Run only ArchiveDate subject
mvn -Pperf -Dbench.subject=ArchiveDate -Dbench.args="KmipSerializationBenchmark" verify

# Run multiple subjects
mvn -Pperf -Dbench.subject=ArchiveDate,ActivationDate -Dbench.args="KmipSerializationBenchmark" verify

# Run a specific benchmark method for a single subject
mvn -Pperf -Dbench.subject=ArchiveDate -Dbench.args="KmipSerializationBenchmark.jsonSerialize" verify

# Quick profile for one subject
mvn -Pperf-fast -Dbench.subject=ArchiveDate -Dbench.args="KmipSerializationBenchmark -f 1 -wi 1 -i 2" verify
```

Notes:

- The runner injects JMH params, so prefer `-Dbench.subject=...` instead of using `-p subject=...` inside `bench.args`.
- Valid subject names match the registered `KmipBenchmarkSubject` names (e.g., `ArchiveDate`, `ActivationDate`).

### Common JMH Options

- `-f <forks>`: Number of forks (default: 1)
- `-i <iterations>`: Number of measurement iterations
- `-wi <warmup>`: Number of warmup iterations
- `-t <threads>`: Number of threads
- `-bm <mode>`: Benchmark mode (Throughput, AverageTime, SampleTime, etc.)

## Benchmark Results

### Latest Performance Metrics (JDK 21)

| Benchmark                | Mode | Cnt | Score | Error | Units |
|--------------------------|------|-----|-------|-------|-------|
| `stateCreation`          | avgt | 5   | 25.7  | ±3.2  | ns/op |
| `stateToString`          | avgt | 5   | 45.2  | ±5.1  | ns/op |
| `activationDateCreation` | avgt | 5   | 32.8  | ±4.5  | ns/op |
| `activationDateToString` | avgt | 5   | 78.3  | ±9.2  | ns/op |
| `createKmipTag`          | avgt | 5   | 8.5   | ±1.2  | ns/op |
| `kmipTagToString`        | avgt | 5   | 35.7  | ±4.8  | ns/op |
| `getKmipSpec`            | avgt | 5   | 2.1   | ±0.3  | ns/op |
| `kmipSpecToString`       | avgt | 5   | 15.3  | ±2.1  | ns/op |
| `isStateActive`          | avgt | 5   | 3.7   | ±0.5  | ns/op |
| `stateFromValue`         | avgt | 5   | 28.4  | ±3.1  | ns/op |
| `stateFromName`          | avgt | 5   | 31.2  | ±3.8  | ns/op |

## Writing Benchmarks

### Basic Benchmark

```java

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StateBenchmark {

    private State state;

    @Setup
    public void setup() {
        KmipContext.setSpec(KmipSpec.V1_4);
        state = new State(State.Standard.ACTIVE);
    }

    @TearDown
    public void tearDown() {
        KmipContext.clear();
    }

    @Benchmark
    public String benchmarkStateToString() {
        return state.toString();
    }
}
```

### Parameterized Benchmark

```java

@State(Scope.Benchmark)
public class ParameterizedBenchmark {

    @Param({"ACTIVE", "DEACTIVATED", "DESTROYED"})
    private String stateName;

    private State state;

    @Setup
    public void setup() {
        KmipContext.setSpec(KmipSpec.V1_4);
        state = new State(State.fromName(KmipSpec.V1_4, stateName));
    }

    @TearDown
    public void tearDown() {
        KmipContext.clear();
    }

    @Benchmark
    public String benchmarkStateToString() {
        return state.toString();
    }
}
```

## Best Practices

1. **Avoid Dead Code Elimination**
    - Use JMH's `Blackhole` to consume results
    - Return values from `@Benchmark` methods

2. **Minimize Measurement Overhead**
    - Keep benchmark methods simple
    - Move setup to `@Setup` methods
    - Use `@State` for shared state

3. **Be Mindful of JIT Optimizations**
    - Run with multiple forks (`-f 3`)
    - Use `-prof gc` to monitor garbage collection

4. **Analyze Results**
    - Look for high variance (error %)
    - Check for warmup effects
    - Compare across different JVM versions

## Profiling

### Using JMH Profilers

```bash
# Enable GC profiler (via bench.args)
mvn -Pperf -Dbench.args="-prof gc" verify

# Enable stack profiler
mvn -Pperf -Dbench.args="-prof stack" verify

# Enable perf_asm (Linux only)
mvn -Pperf -Dbench.args="-prof perfasm" verify
```

### Common Issues

1. **High Allocation Rate**
    - Look for unnecessary object creation
    - Consider object pooling for frequently created objects

2. **Contention**
    - Check for thread contention in concurrent benchmarks
    - Use `-t` to vary thread count

3. **Warmup Effects**
    - Increase warmup iterations (`-wi`)
    - Check if results stabilize across iterations
