package org.purpleBean.kmip.benchmark;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Runner for JMH benchmarks.
 * 
 * <p>Run with no arguments to execute all benchmarks, or specify a specific benchmark class/method
 * to run only that benchmark.</p>
 * 
 * <p>Example usage:
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
        if (args.length == 0) {
            // Default to running all benchmarks with reasonable defaults
            Options opt = new OptionsBuilder()
                .include(KmipSerializationBenchmark.class.getSimpleName())
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(1))
                .forks(1)
                .timeUnit(TimeUnit.MICROSECONDS)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
            
            new Runner(opt).run();
        } else {
            // Delegate to JMH Main for other cases
            try {
                Main.main(args);
            } catch (IOException e) {
                System.err.println("Error executing benchmarks: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}
