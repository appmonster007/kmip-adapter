package org.purpleBean.kmip.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates a markdown report of test execution statistics.
 */
public class TestReportGenerator {

    private final TestExecutionStatsListener statsListener;
    private final String reportPath;

    public TestReportGenerator(TestExecutionStatsListener statsListener, String reportPath) {
        this.statsListener = statsListener;
        this.reportPath = reportPath;
    }

    public void generateReport() throws IOException {
        Map<String, TestExecutionStatsListener.TestStats> stats = statsListener.getTestStats();

        // Sort by average duration (descending)
        var sortedStats =
                stats.entrySet().stream()
                        .sorted(Comparator.comparingDouble(e -> -e.getValue().getAverageDuration()))
                        .collect(Collectors.toList());

        // Create reports directory if it doesn't exist
        Path reportsDir = Paths.get("target/reports");
        if (!Files.exists(reportsDir)) {
            Files.createDirectories(reportsDir);
        }

        // Write markdown report
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportPath))) {
            writer.write("# Test Execution Report\n\n");
            writer.write(
                    "Generated on: "
                            + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            + "\n\n");

            // Summary
            writer.write("## Summary\n\n");
            writer.write("| Metric | Count |\n");
            writer.write("|--------|-------|\n");
            writer.write(String.format("| Total Tests | %d |\n", stats.size()));
            writer.write(
                    String.format(
                            "| Total Executions | %d |\n",
                            stats.values().stream()
                                    .mapToInt(TestExecutionStatsListener.TestStats::getExecutionCount)
                                    .sum()));
            writer.write(
                    String.format(
                            "| Success Rate | %.2f%% |\n",
                            stats.values().stream()
                                    .mapToInt(TestExecutionStatsListener.TestStats::getSuccessCount)
                                    .sum()
                                    * 100.0
                                    / stats.values().stream()
                                    .mapToInt(TestExecutionStatsListener.TestStats::getExecutionCount)
                                    .sum()));

            // Slowest tests
            writer.write("\n## Slowest Tests\n\n");
            writer.write(
                    "| Test | Executions | Success | Failures | Aborted | Avg Time (ms) | Min (ms) | Max (ms) |\n");
            writer.write(
                    "|------|------------|---------|----------|---------|--------------|----------|----------|\n");

            int count = 0;
            for (var entry : sortedStats) {
                if (count++ >= 10) break; // Top 10 slowest
                TestExecutionStatsListener.TestStats s = entry.getValue();
                writer.write(
                        String.format(
                                "| %s | %d | %d | %d | %d | %.2f | %d | %d |\n",
                                entry.getKey(),
                                s.getExecutionCount(),
                                s.getSuccessCount(),
                                s.getFailureCount(),
                                s.getAbortedCount(),
                                s.getAverageDuration(),
                                s.getMinDuration(),
                                s.getMaxDuration()));
            }

            // All tests
            writer.write("\n## All Tests\n\n");
            writer.write("| Test | Executions | Success | Failures | Aborted | Avg Time (ms) |\n");
            writer.write("|------|------------|---------|----------|---------|--------------|\n");

            for (var entry : sortedStats) {
                TestExecutionStatsListener.TestStats s = entry.getValue();
                writer.write(
                        String.format(
                                "| %s | %d | %d | %d | %d | %.2f |\n",
                                entry.getKey(),
                                s.getExecutionCount(),
                                s.getSuccessCount(),
                                s.getFailureCount(),
                                s.getAbortedCount(),
                                s.getAverageDuration()));
            }
        }
    }
}
