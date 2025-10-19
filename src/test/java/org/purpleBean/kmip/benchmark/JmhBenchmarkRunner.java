package org.purpleBean.kmip.benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.purpleBean.kmip.benchmark.report.JmhReportGenerator;
import org.purpleBean.kmip.benchmark.util.BenchmarkSubjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Standalone JMH runner to execute benchmarks only when explicitly invoked.
 * This is not picked up by Surefire/Failsafe.
 * <p>
 * Usage examples:
 * - mvn -q -DskipTests test-compile exec:java -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner"
 * - mvn -q -DskipTests verify -P perf
 * - mvn -q -DskipTests verify -P perf-fast
 */
public final class JmhBenchmarkRunner {

    private JmhBenchmarkRunner() {
    }

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

        // Parallelism and timing are configurable via system properties
        int threads = Integer.getInteger("bench.threads", Math.max(1, Runtime.getRuntime().availableProcessors()));
        int forks = Integer.getInteger("bench.forks", 0);
        int warmupIters = Integer.getInteger("bench.wi", 2);
        int measureIters = Integer.getInteger("bench.mi", 3);
        long warmupMs = Long.getLong("bench.wt.ms", 10L);
        long measureMs = Long.getLong("bench.mt.ms", 10L);

        // Auto-discover benchmark subjects via ServiceLoader and pass them as JMH @Param values
        List<String> subjects = BenchmarkSubjects.discoverNames();

        if (subjects.isEmpty()) {
            throw new IllegalStateException("No KmipBenchmarkSubject implementations discovered via ServiceLoader. " +
                    "Ensure service entries exist under src/test/resources/META-INF/services/" +
                    "org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject");
        }
        System.out.println("JMH Runner discovered subjects: " + subjects);

        // Allow selecting a subset or a single subject via -Dbench.subject=ArchiveDate[,ActivationDate]
        String subjectProp = System.getProperty("bench.subject", "").trim();
        String[] chosenSubjects;
        if (!subjectProp.isEmpty()) {
            String[] requested = subjectProp.split(",");
            for (int i = 0; i < requested.length; i++) {
                requested[i] = requested[i].trim();
                if (!subjects.contains(requested[i])) {
                    throw new IllegalArgumentException("Unknown subject '" + requested[i] + "'. Available: " + subjects);
                }
            }
            chosenSubjects = requested;
            System.out.println("JMH Runner filtered subjects via bench.subject: " + String.join(", ", requested));
        } else {
            chosenSubjects = subjects.toArray(String[]::new);
        }

        System.out.printf("JMH config => threads=%d, forks=%d, wi=%d, wtMs=%d, mi=%d, mtMs=%d, timeUnit=%s%n",
                threads, forks, warmupIters, warmupMs, measureIters, measureMs, TimeUnit.MICROSECONDS);

        ChainedOptionsBuilder builder = new OptionsBuilder()
                .include(include)
                .warmupIterations(warmupIters)
                .warmupTime(TimeValue.milliseconds(warmupMs))
                .measurementIterations(measureIters)
                .measurementTime(TimeValue.milliseconds(measureMs))
                // Run in-process to avoid classpath issues when using exec:java (configurable via bench.forks)
                .forks(forks)
                .threads(threads)
                .timeUnit(TimeUnit.MICROSECONDS)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-Xmx512m", "-Xms512m")
                .param("subject", chosenSubjects)
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
