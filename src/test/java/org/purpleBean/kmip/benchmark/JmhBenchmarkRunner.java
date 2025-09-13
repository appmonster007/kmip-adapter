package org.purpleBean.kmip.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * Runner for JMH benchmarks.
 *
 * <p>Run with no arguments to execute all benchmarks, or specify a specific benchmark class/method
 * to run only that benchmark.
 *
 * <p>Example usage:
 *
 * <pre>
 * # Run all benchmarks
 * mvn test-compile exec:java -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner"
 *
 * # Run a specific benchmark
 * mvn test-compile exec:java -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
 *     -Dexec.args="KmipSerializationBenchmark"
 * </pre>
 */
public class JmhBenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
        try {
            Options opt =
                    new OptionsBuilder()
                            .include(KmipSerializationBenchmark.class.getSimpleName())
                            .warmupIterations(3)
                            .warmupTime(TimeValue.seconds(1))
                            .measurementIterations(5)
                            .measurementTime(TimeValue.seconds(1))
                            .forks(0) // Run in the same JVM
                            .threads(1)
                            .timeUnit(TimeUnit.MICROSECONDS)
                            .shouldFailOnError(true)
                            .shouldDoGC(true)
                            .jvmArgs("-Xmx512m", "-Xms512m")
                            .build();

            System.out.println("Starting benchmarks with options: " + opt);
            new Runner(opt).run();
        } catch (Exception e) {
            System.err.println("Error executing benchmarks: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
