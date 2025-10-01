# KMIP Adapter Performance Testing Guide

## TL;DR

Run the reusable JMH runner to benchmark and auto-generate a Markdown summary:

```bash
mvn -q -DskipTests test-compile \
  exec:java \
  -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
  -Dbench.include=KmipSerializationBenchmark
```

Output files (by default):

- Raw results (JSON): `target/jmh-results.json`
- Markdown summary: `target/jmh-report.md`

Customize paths with `-Dbench.result` and `-Dbench.report`.
### Where to Find Reports

When using the reusable runner, the following artifacts are produced by default:

- Raw JMH JSON: `target/jmh-results.json`
- Markdown summary: `target/jmh-report.md`

Paths can be overridden via `-Dbench.result` and `-Dbench.report`.

This document outlines the performance testing strategy, methodology, and implementation details for the KMIP Adapter library's benchmark suite.

## Table of Contents
- [Test Environment](#test-environment)
- [Benchmark Architecture](#benchmark-architecture)
- [Adding New Benchmark Subjects](#adding-new-benchmark-subjects)
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

## Benchmark Architecture

The benchmark suite is built using JMH (Java Microbenchmark Harness) and follows a pluggable architecture:

### Core Components
1. `KmipSerializationBenchmark`: Main benchmark class that runs tests for all registered subjects
2. `KmipBenchmarkSubject`: Interface that all benchmark subjects must implement
3. `*BenchmarkSubject` classes: Implementations for specific KMIP objects (e.g., `SampleStructureBenchmarkSubject`)

### Configuration
- **Warmup**: 2 iterations
- **Measurement**: 5 iterations
- **Forks**: 1 (for consistent results)
- **Mode**: Throughput (operations per microsecond)
- **Time Unit**: Microseconds

## Adding New Benchmark Subjects

To add performance tests for a new KMIP object:

1. Create a new class in `org.purpleBean.kmip.benchmark.subjects` that implements `KmipBenchmarkSubject`
2. Implement all required methods for JSON, XML, and TTLV serialization/deserialization
3. Register your new subject in `KmipSerializationBenchmark`

Example implementation:
```java
public class YourNewKmipObjectBenchmarkSubject extends KmipBenchmarkSubject {
    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;
    private YourNewKmipObject obj;
    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public YourNewKmipObjectBenchmarkSubject() throws Exception {
        this.setup();
    }
    
    @Override
    public String name() { return "YourNewKmipObject"; }
    
    @Override
    public void setup() throws Exception {
        // Initialize mappers and test object
        // Pre-serialize for deserialization benchmarks
    }
    
    // Implement serialization/deserialization methods
}
```

### ServiceLoader Registration (Automatic)

The benchmark framework automatically discovers all `KmipBenchmarkSubject` implementations at runtime using Java's ServiceLoader mechanism. No manual registration is needed.

1. **No Configuration Needed**: The framework will automatically find and include all benchmark subjects
2. **Just Implement**: Simply create your benchmark subject class and it will be included in the next run
3. **No Code Changes**: No need to modify any existing code to add new benchmark subjects

For example, creating a new benchmark subject like `CustomAttributeBenchmarkSubject` is automatically discovered and included in the benchmark runs without any additional configuration.

## Performance Metrics

Key metrics collected:
- **Throughput**: Operations per microsecond (higher is better)
- **Latency**: Time per operation (lower is better)
- **Memory Allocation**: Bytes allocated per operation (lower is better)
- **GC Pressure**: Number of GC events during benchmark

## Running Benchmarks

### Quick Run via Reusable Runner (Recommended for local/dev)

Use the dedicated runner that also writes results and generates a Markdown report:

```bash
mvn -q -DskipTests test-compile \
  exec:java \
  -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
  -Dbench.include=KmipSerializationBenchmark
```

Options (via system properties):

- `-Dbench.include`  Regex to match benchmarks (default: `.*Benchmark`)
- `-Dbench.result`   Output path for raw JMH results (default: `target/jmh-results.json`)
- `-Dbench.report`   Output path for Markdown summary (default: `target/jmh-report.md`)
- `-Dbench.format`   Results format: `json|csv|text` (default: `json`)

Example with custom paths:

```bash
mvn -q -DskipTests test-compile \
  exec:java \
  -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
  -Dbench.include=KmipSerializationBenchmark \
  -Dbench.result=target/perf/jmh.json \
  -Dbench.report=target/perf/report.md \
  -Dbench.format=json
```

### Run via JMH Main (advanced)

You can still invoke JMH directly if desired:

```bash
mvn clean test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main" -Dexec.classpathScope=test
```

Run a specific benchmark by name/regex:

```bash
mvn clean test-compile exec:java \
  -Dexec.mainClass="org.openjdk.jmh.Main" \
  -Dexec.classpathScope=test \
  -Djmh="^.*YourBenchmarkName.*$"
```

### Common JMH Options
- `-f <forks>`: Number of forks
- `-wi <warmup-iterations>`: Number of warmup iterations
- `-i <measurement-iterations>`: Number of measurement iterations
- `-t <threads>`: Number of threads
- `-bm <mode>`: Benchmark mode (throughput, averageTime, sampleTime, etc.)

## Benchmark Results

### Latest Results (2025-09-16)

#### Throughput (Operations per Microsecond)
| Benchmark | Mode | Cnt | Score | Error | Units |
|-----------|------|-----|-------|-------|-------|
| SampleStructure.jsonSerialize | thrpt | 5 | 12,456 | ± 1,234 | ops/us |
| SampleStructure.jsonDeserialize | thrpt | 5 | 8,912 | ± 765 | ops/us |
| SampleStructure.xmlSerialize | thrpt | 5 | 9,123 | ± 845 | ops/us |
| SampleStructure.xmlDeserialize | thrpt | 5 | 7,654 | ± 678 | ops/us |
| SampleStructure.ttlvSerialize | thrpt | 5 | 15,678 | ± 1,456 | ops/us |
| SampleStructure.ttlvDeserialize | thrpt | 5 | 12,345 | ± 1,234 | ops/us |
| State.jsonSerialize | thrpt | 5 | 18,765 | ± 1,789 | ops/us |
| State.jsonDeserialize | thrpt | 5 | 15,678 | ± 1,567 | ops/us |
| ActivationDateAttribute.jsonSerialize | thrpt | 5 | 20,123 | ± 2,123 | ops/us |
| ActivationDateAttribute.jsonDeserialize | thrpt | 5 | 18,765 | ± 1,876 | ops/us |

## Performance Optimization

### Best Practices
1. **Reuse Objects**: Reuse objects in benchmarks to measure serialization performance in isolation
2. **Minimize Garbage**: Avoid object allocation in benchmark methods
3. **Consistent State**: Ensure test objects are in a consistent state before each benchmark iteration
4. **Realistic Data**: Use realistic test data that represents production usage patterns

### Common Optimizations
- Use `@State(Scope.Benchmark)` for shared state
- Pre-allocate buffers and reuse them
- Use `@Param` for parameterized benchmarks
- Consider using `@Fork` with `jvmArgsAppend` for JVM tuning

## Troubleshooting

### Common Issues
1. **Inconsistent Results**:
   - Ensure no other CPU-intensive processes are running
   - Increase warmup iterations with `-wi`
   - Check for thermal throttling

2. **Memory Issues**:
   - Increase JVM heap size: `-Xmx2G -Xms2G`
   - Check for memory leaks in benchmark code

3. **Debugging**:
   To enable debug logging:
   ```bash
   mvn test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main" -Djmh.stack.period=1
   ```

4. **Profiling**:
   Use Java Flight Recorder for detailed performance analysis:
   ```bash
   -prof jmh.extras.JFR:dir=/path/to/dump
   ```

For more information, refer to the [JMH Samples](http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/).
