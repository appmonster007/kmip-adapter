package org.purpleBean.kmip.benchmark.report;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.purpleBean.kmip.benchmark.util.BenchmarkSubjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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

        Map<String, SubjectMetaInfo> subjectMetaInfoMap = new HashMap<>();
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

            subjectMetaInfoMap.putIfAbsent(subject, new SubjectMetaInfo(
                    subject,
                    BenchmarkSubjects.getJsonStr(subject),
                    BenchmarkSubjects.getXmlStr(subject),
                    BenchmarkSubjects.getTtlvBuf(subject))
            );
            rows.add(new Row(benchmark, subject, score, scoreError, scoreUnit, mode));
        }

        // Group by subject for readability
        Map<String, List<Row>> bySubject = rows.stream()
                .collect(Collectors.groupingBy(r -> r.subject, TreeMap::new, Collectors.toList()));

        StringBuilder md = new StringBuilder();
        md.append("# KMIP Serialization Benchmarks\n\n");
        md.append(String.format("Generated from JMH JSON: [%s](%s)  ", jsonPath, in.getFileName()));
        AtomicInteger index = new AtomicInteger(1);
        for (Map.Entry<String, List<Row>> e : bySubject.entrySet()) {
            String k = e.getKey();
            md.append(String.format("\n%d. [%s](#%s)  ", index.getAndIncrement(), k, k));
        }
        md.append("\n\n");

        for (var entry : bySubject.entrySet()) {
            String subject = entry.getKey();
            md.append(String.format("<a id=\"%s\"></a>\n", subject));
            md.append("## ").append(subject)
                    .append("\n")
                    .append(String.format("\nJSON: %s  ", subjectMetaInfoMap.get(subject).jsonStr))
                    .append(String.format("\nXML: %s  ", subjectMetaInfoMap.get(subject).xmlStr).replace("<", "&lt;").replace(">", "&gt;"))
                    .append(String.format("\nTTLV: %s  ", subjectMetaInfoMap.get(subject).ttlvStr))
                    .append("\n\n");
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

    private record SubjectMetaInfo(String subject, String jsonStr, String xmlStr, String ttlvStr) {
    }
}
