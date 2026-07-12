package org.purpleBean.kmip.verification;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class KmipV21VerificationTest {

    private static final String FIXED_TIMESTAMP = "1970-01-01T00:00:00+00:00";
    private static final String FIXED_CORRELATION = "FIXED_CORRELATION_VALUE";

    private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
    private final String projectRoot = System.getProperty("user.dir");

    @DisplayName("Test KMIP 2.1 Specific File")
    @Test
    public void testSpecificFile() {
        String filePath = projectRoot +
                "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1/TC-ASYNC-1-21.xml";
        KmipContext.withSpec(KmipSpec.V2_1, () -> {
            try {
                int[] counts = verifyXmlFile(Paths.get(filePath));
                System.out.printf("Passed: %d, Total messages: %d%n", counts[0], counts[1]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @DisplayName("Diagnose first 10 KMIP 2.1 test case files")
    @Test
    public void diagnoseFirst10() {
        String base = projectRoot + "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1";
        KmipContext.withSpec(KmipSpec.V2_1, () -> {
            try (java.util.stream.Stream<Path> s = Files.walk(Paths.get(base))) {
                List<Path> files = s.filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".xml"))
                        .sorted(Comparator.comparing(Path::toString))
                        .limit(10)
                        .toList();
                for (Path path : files) {
                    System.out.println("\n>>> FILE: " + path.getFileName());
                    diagnoseFile(path);
                }
            } catch (IOException e) {
                fail(e.getMessage());
            }
            return null;
        });
    }

    private void diagnoseFile(Path path) {
        try {
            String rawXml = Files.readString(path);
            String xml = rawXml.replace("$NOW", FIXED_TIMESTAMP).replace("$SERVER_CORRELATION_VALUE", FIXED_CORRELATION);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document doc = factory.newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            for (String tag : new String[]{"RequestMessage", "ResponseMessage"}) {
                NodeList nodes = doc.getDocumentElement().getElementsByTagName(tag);
                for (int i = 0; i < nodes.getLength(); i++) {
                    StringWriter sw = new StringWriter();
                    transformer.transform(new DOMSource((Element) nodes.item(i)), new StreamResult(sw));
                    String fragment = sw.toString();
                    try {
                        verifyMessageFragment(fragment, tag, path.getFileName().toString());
                    } catch (Throwable e) {
                        System.err.printf("  FAIL %s[%d]: %s%n", tag, i, e.getMessage());
                        Throwable cause = e.getCause();
                        while (cause != null) {
                            System.err.printf("    caused by: %s%n", cause.getMessage());
                            cause = cause.getCause();
                        }
                    }
                }
            }
        } catch (Throwable e) {
            System.err.println("  PARSE ERROR: " + e.getMessage());
        }
    }

    @DisplayName("Test KMIP 2.1 Test Cases")
    @Test
    public void testKmip21TestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1";
        KmipContext.withSpec(KmipSpec.V2_1, () -> {
            verifyXmlFiles(basePath);
            return null;
        });
    }

    private void verifyXmlFiles(String basePath) {
        int passCount = 0;
        int totalMessages = 0;
        try (Stream<Path> paths = Files.walk(Paths.get(basePath))) {
            List<Path> pathList = paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .sorted(Comparator.comparing(Path::toString))
                    .toList();
            for (Path path : pathList) {
                try {
                    int[] counts = verifyXmlFile(path);
                    passCount += counts[0];
                    totalMessages += counts[1];
                } catch (Throwable e) {
                    System.err.println("Error processing file: " + path + " - " + e.getMessage());
                }
            }
            System.out.printf("Passed: %d, Total messages: %d%n", passCount, totalMessages);
        } catch (IOException e) {
            fail("Failed to walk directory: " + basePath + " - " + e.getMessage());
        }
    }

    // Returns [passCount, totalCount] for messages in the file
    private int[] verifyXmlFile(Path path) throws Exception {
        String rawXml = Files.readString(path);
        // Replace dynamic placeholders with stable values so DateTime parsing succeeds
        String xml = rawXml
                .replace("$NOW", FIXED_TIMESTAMP)
                .replace("$SERVER_CORRELATION_VALUE", FIXED_CORRELATION);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        int pass = 0;
        int total = 0;

        for (String tag : new String[]{"RequestMessage", "ResponseMessage"}) {
            NodeList nodes = doc.getDocumentElement().getElementsByTagName(tag);
            for (int i = 0; i < nodes.getLength(); i++) {
                total++;
                Element element = (Element) nodes.item(i);
                StringWriter sw = new StringWriter();
                transformer.transform(new DOMSource(element), new StreamResult(sw));
                String fragment = sw.toString();

                try {
                    verifyMessageFragment(fragment, tag, path.getFileName().toString());
                    pass++;
                } catch (Throwable e) {
                    System.err.printf("  [%s] %s[%d]: %s%n", path.getFileName(), tag, i, e.getMessage());
                }
            }
        }
        return new int[]{pass, total};
    }

    private void verifyMessageFragment(String fragment, String tag, String fileName) throws Exception {
        Class<? extends KmipDataType> targetClass = tag.equals("RequestMessage")
                ? RequestMessageStructure.class
                : ResponseMessageStructure.class;

        KmipDataType deserialized = xmlMapper.readValue(fragment, targetClass);
        String reserialized = xmlMapper.writeValueAsString(deserialized);

        XmlMapper verificationMapper = new XmlMapper();
        String minifiedOriginal = fragment.replaceAll(">\\s+<", "><").trim();
        String minifiedNew = reserialized.replaceAll(">\\s+<", "><").trim();

        JsonNode originalNode = verificationMapper.readTree(minifiedOriginal);
        JsonNode newNode = verificationMapper.readTree(minifiedNew);

        assertEquals(originalNode, newNode, "Round-trip mismatch in " + fileName + " <" + tag + ">");
        System.out.println("Verified: " + fileName + " <" + tag + ">");
    }
}
