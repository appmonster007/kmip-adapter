package org.purpleBean.kmip.documentation;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.benchmark.util.BenchmarkSubjects;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;

import javax.xml.stream.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.TreeMap;

class AllFormatsSerializationTest {

    @Test
    void documentAllFormats() {
        Map<String, Map<String, String>> results = new TreeMap<>();
        HexFormat hexFormat = HexFormat.of();
        BenchmarkSubjects.discoverMap().forEach((name, subject) -> {
            try {
                Map<String, String> subjectResults = new HashMap<>();
                subjectResults.put("spec", subject.getSpec().toString());
                subjectResults.put("class", subject.getType().getName());

                subjectResults.put("json", subject.getJsonStr());
                subjectResults.put("xml", subject.getXmlStr());
                subjectResults.put("ttlv", hexFormat.formatHex(subject.getTtlvBuf().array()));

                results.put(name, subjectResults);

            } catch (Exception e) {
                Map<String, String> failureResult = new HashMap<>();
                failureResult.put("error", e.toString());
                results.put(name, failureResult);
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("# KMIP All Formats Serialization\n\n");

        sb.append("| # | Subject | Spec | Class | JSON | XML | TTLV |\n");
        sb.append("|---|---|---|---|---|---|---|\n");

        int idx = 0;
        for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {
            idx += 1;
            String subjectName = entry.getKey();
            Map<String, String> subjectResults = entry.getValue();

            if (subjectResults.containsKey("error")) {
                sb.append("| ").append(subjectName).append(" | | | | | | ").append(escapeHtml(subjectResults.get("error"))).append(" |\n");
            } else {
                sb.append("| ").append(idx).append(" | ");
                sb.append(subjectName).append(" | `");
                sb.append(subjectResults.get("spec")).append("` | `");
                sb.append(subjectResults.get("class")).append("` | ");
//                sb.append("<details><summary>JSON</summary><pre><code>").append(escapeHtml(subjectResults.get("json"))).append("</code></pre></details> | ");
                sb.append("`").append(escapeHtml(subjectResults.get("json"))).append("` | ");
                sb.append("`").append(subjectResults.get("xml")).append("` | ");
                sb.append("`").append(subjectResults.get("ttlv")).append("` |\n");
            }
        }

        File outputFile = new File("build/reports/kmip/serialization.md");
        outputFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void documentAllStructuredFormats() {
        Map<String, Map<String, String>> results = new TreeMap<>();
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper xmlMapper = new XmlMapper();

        BenchmarkSubjects.discoverMap().forEach((name, subject) -> {
            try {
                Map<String, String> subjectResults = new HashMap<>();
                subjectResults.put("spec", subject.getSpec().toString());
                subjectResults.put("class", subject.getType().getName());
                subjectResults.put("json", prettyJson(subject.getJsonStr()));
                subjectResults.put("xml", prettyXml(subject.getXmlStr()));
                subjectResults.put("ttlv", TtlvObject.fromBuffer(subject.getTtlvBuf()).getStructuredByteString());

                results.put(name, subjectResults);

            } catch (Exception e) {
                Map<String, String> failureResult = new HashMap<>();
                failureResult.put("error", e.toString());
                results.put(name, failureResult);
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append("# KMIP All Formats Serialization\n\n");

        int idx = 0;
        for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {
            idx += 1;
            String subjectName = entry.getKey();
            Map<String, String> subjectResults = entry.getValue();

            sb.append("## ").append(idx).append(". ").append(subjectName).append("\n\n");

            if (subjectResults.containsKey("error")) {
                sb.append("**FAILED**\n\n");
                sb.append("```\n");
                sb.append(subjectResults.get("error")).append("\n");
                sb.append("```\n\n");
            } else {
                sb.append("- **Spec:** `").append(subjectResults.get("spec")).append("`\n");
                sb.append("- **Class:** `").append(subjectResults.get("class")).append("`\n\n");

                sb.append("### JSON\n\n");
                sb.append("```json\n");
                sb.append(subjectResults.get("json")).append("\n");
                sb.append("```\n\n");

                sb.append("### XML\n\n");
                sb.append("```xml\n");
                sb.append(subjectResults.get("xml")).append("\n");
                sb.append("```\n\n");

                sb.append("### TTLV\n\n");
                sb.append("```\n");
                sb.append(subjectResults.get("ttlv")).append("\n");
                sb.append("```\n\n");
            }
        }

        File outputFile = new File("build/reports/kmip/serialization_formatted.md");
        outputFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private String prettyJson(String jsonMinify) throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(jsonMinify);
        StringWriter writer = new StringWriter();
        JsonGenerator generator = factory.createGenerator(writer);
        generator.useDefaultPrettyPrinter();  // Indents without objects
        while (parser.nextToken() != null) {
            generator.copyCurrentEvent(parser);
        }
        generator.close();
        return writer.toString();
    }

    private String prettyXml(String xmlMinify) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        Source source = new StreamSource(new StringReader(xmlMinify));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));
        return output.toString();
    }
}
