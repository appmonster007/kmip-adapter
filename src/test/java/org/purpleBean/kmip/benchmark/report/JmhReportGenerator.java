package org.purpleBean.kmip.benchmark.report;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility to transform JMH JSON results into a concise Markdown summary report.
 */
public final class JmhReportGenerator {

    private JmhReportGenerator() {
    }

    public static void generateMarkdownSummary(String jsonPath, String mdOutPath) throws IOException {
        Path in = Path.of(jsonPath);
        if (!Files.exists(in)) {
            throw new IOException("JMH results not found: " + jsonPath);
        }
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> results = mapper.readValue(Files.readString(in), new TypeReference<List<JsonNode>>() {
        });

        List<Row> rows = new ArrayList<>();
        for (JsonNode node : results) {
            String benchmark = node.path("benchmark").asText("");
            JsonNode params = node.path("params");
            String subject = params.path("subject").asText("-");
            JsonNode primary = node.path("primaryMetric");
            String score = primary.path("score").asText("-");
            String scoreUnit = primary.path("scoreUnit").asText("");
            String scoreError = primary.path("scoreError").asText("-");
            String mode = node.path("mode").asText("");

            rows.add(new Row(benchmark, subject, score, scoreError, scoreUnit, mode));
        }

        // Group by subject for readability
        Map<String, List<Row>> bySubject = rows.stream().collect(Collectors.groupingBy(r -> r.subject));

        StringBuilder md = new StringBuilder();
        md.append("# KMIP Serialization Benchmarks\n\n");
        md.append("Generated from JMH JSON: ").append(jsonPath).append("\n\n");

        for (var entry : bySubject.entrySet()) {
            String subject = entry.getKey();
            md.append("## ").append(subject).append("\n\n");
            md.append("Benchmark | Mode | Score | Error margin (99.9%) | Unit\n");
            md.append("---|---|---:|---:|---\n");
            for (Row r : entry.getValue()) {
                md.append(r.simpleName()).append(" | ")
                        .append(r.mode).append(" | ")
                        .append(r.score).append(" | ")
                        .append(r.error).append(" | ")
                        .append(r.unit).append("\n");
            }
            md.append("\n");
        }

        Path out = Path.of(mdOutPath);
        Files.createDirectories(out.getParent());
        Files.writeString(out, md.toString());
    }

    private record Row(String benchmark, String subject, String score, String error, String unit, String mode) {
        String simpleName() {
            int idx = benchmark.lastIndexOf('.');
            return idx >= 0 ? benchmark.substring(idx + 1) : benchmark;
        }
    }
}
