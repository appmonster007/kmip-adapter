package org.purpleBean.kmip.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.purpleBean.kmip.KmipSpec;

import java.util.concurrent.TimeUnit;

/**
 * Base class for all performance tests.
 */
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public abstract class BasePerformanceTest {

    protected KmipSpec spec = KmipSpec.V1_2;

    @Setup(Level.Trial)
    public void setup() {
        // Common setup for all performance tests
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        // Common cleanup
    }

    /**
     * Example benchmark method - override this in concrete test classes
     */
    @Benchmark
    public void benchmarkMethod(Blackhole bh) {
        // Default implementation - override in subclasses
    }

    protected void preventOptimization(Blackhole bh, Object result) {
        // Use Blackhole to prevent JVM optimizations
        bh.consume(result);
    }
}
