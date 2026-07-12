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

public class KmipV30VerificationTest {

    private static final String FIXED_TIMESTAMP = "1970-01-01T00:00:00+00:00";

    private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
    private final String projectRoot = System.getProperty("user.dir");

    @DisplayName("Test KMIP 3.0 Specific File")
    @Test
    public void testSpecificFile() {
        String filePath = projectRoot +
                "/docs/kmip-spec/v3.x/kmip-profiles/v3.0/csd01/test-cases/kmip-v3.0/mandatory/BL-M-1-30.xml";
        KmipContext.withSpec(KmipSpec.V3_0, () -> {
            try {
                int[] counts = verifyXmlFile(Paths.get(filePath));
                System.out.printf("Passed: %d, Total messages: %d%n", counts[0], counts[1]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }

    @DisplayName("Test KMIP 3.0 Mandatory Test Cases")
    @Test
    public void testKmip30MandatoryTestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v3.x/kmip-profiles/v3.0/csd01/test-cases/kmip-v3.0/mandatory";
        KmipContext.withSpec(KmipSpec.V3_0, () -> {
            verifyXmlFiles(basePath, "mandatory");
            return null;
        });
    }

    @DisplayName("Test KMIP 3.0 Optional Test Cases")
    @Test
    public void testKmip30OptionalTestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v3.x/kmip-profiles/v3.0/csd01/test-cases/kmip-v3.0/optional";
        KmipContext.withSpec(KmipSpec.V3_0, () -> {
            verifyXmlFiles(basePath, "optional");
            return null;
        });
    }

    private void verifyXmlFiles(String basePath, String label) {
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
            System.out.printf("[%s] Passed: %d, Total messages: %d%n", label, passCount, totalMessages);
        } catch (IOException e) {
            fail("Failed to walk directory: " + basePath + " - " + e.getMessage());
        }
    }

    // Returns [passCount, totalCount] for messages extracted from the file
    private int[] verifyXmlFile(Path path) throws Exception {
        String rawXml = Files.readString(path);
        // $NOW is a placeholder for the server timestamp; replace with a stable value
        String xml = rawXml.replace("$NOW", FIXED_TIMESTAMP);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

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
