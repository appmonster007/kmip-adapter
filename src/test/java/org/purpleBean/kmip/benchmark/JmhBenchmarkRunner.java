package org.purpleBean.kmip.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.results.format.ResultFormatType;

import org.purpleBean.kmip.benchmark.report.JmhReportGenerator;
import org.purpleBean.kmip.benchmark.util.BenchmarkSubjects;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Standalone JMH runner to execute benchmarks only when explicitly invoked.
 * This is not picked up by Surefire/Failsafe.
 *
 * Usage examples:
 *  - mvn -q -DskipTests test-compile exec:java -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner"
 *  - mvn -q -DskipTests verify -P perf
 *  - mvn -q -DskipTests verify -P perf-fast
 */
public final class JmhBenchmarkRunner {

    private JmhBenchmarkRunner() {}

    public static void main(String[] args) throws RunnerException {
        // Allow overrides via -Dbench.args="-wi 2 -i 3 -f 1"
        String include = System.getProperty("bench.include", ".*Benchmark");
        String resultPath = System.getProperty("bench.result", "target/jmh-results.json");
        String resultFormatProp = System.getProperty("bench.format", "json");
        String reportPath = System.getProperty("bench.report", "target/jmh-report.md");
        ResultFormatType resultFormat = switch (resultFormatProp.toLowerCase()) {
            case "csv" -> ResultFormatType.CSV;
            case "text" -> ResultFormatType.TEXT;
            case "json" -> ResultFormatType.JSON;
            default -> ResultFormatType.JSON;
        };

        // Auto-discover benchmark subjects via ServiceLoader and pass them as JMH @Param values
        List<String> subjects = BenchmarkSubjects.discoverNames();
        if (subjects.isEmpty()) {
            throw new IllegalStateException("No KmipBenchmarkSubject implementations discovered via ServiceLoader. " +
                    "Ensure service entries exist under src/test/resources/META-INF/services/" +
                    "org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject");
        }
        System.out.println("JMH Runner discovered subjects: " + subjects);

        ChainedOptionsBuilder builder = new OptionsBuilder()
                .include(include)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(1))
                // Run in-process to avoid classpath issues when using exec:java
                .forks(0)
                .threads(1)
                .timeUnit(TimeUnit.MICROSECONDS)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-Xmx512m", "-Xms512m")
                .param("subject", subjects.toArray(String[]::new))
                .result(resultPath)
                .resultFormat(resultFormat);

        Options opt = builder.build();
        System.out.println("Starting JMH benchmarks with include: " + include);
        new Runner(opt).run();
        try {
            if (resultFormat == ResultFormatType.JSON) {
                JmhReportGenerator.generateMarkdownSummary(resultPath, reportPath);
                System.out.println("JMH report written to: " + reportPath);
            } else {
                System.out.println("JMH results written to: " + resultPath + " (format: " + resultFormat + ")");
            }
        } catch (Exception e) {
            System.err.println("Failed to generate JMH report: " + e.getMessage());
        }
    }
}
