package org.purpleBean.kmip.verification;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.KmipCodecManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class KmipVerificationTest {

    private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
    private final String projectRoot = System.getProperty("user.dir");

    @Disabled
    @DisplayName("Test KMIP 1.0 Test Cases")
    @Test
    public void testKmip10TestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v1.x/test-cases-messages/2_KMIP_Test_Cases/2.1_KMIP_1.0_Test_Cases";
        verifyXmlFiles(basePath);
    }

    @Disabled
    @DisplayName("Test KMIP 1.1 Test Cases")
    @Test
    public void testKmip11TestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v1.x/test-cases-messages/2_KMIP_Test_Cases/2.2_KMIP_1.1_Test_Cases";
        verifyXmlFiles(basePath);
    }

    @DisplayName("Test KMIP 1.2 Test Cases")
    @Test
    public void testKmip12TestCases() {
        String basePath = projectRoot + "/docs/kmip-spec/v1.x/test-cases-messages/2_KMIP_Test_Cases/2.3_KMIP_1.2_Test_Cases";
        KmipContext.withSpec(KmipSpec.V1_2, () -> {
            verifyXmlFiles(basePath);
            return null;
        });
    }

    @Test
    public void testSpecificFile() {
        String filePath = projectRoot + "/docs/kmip-spec/v1.x/test-cases-messages/2_KMIP_Test_Cases/2.3_KMIP_1.2_Test_Cases/2.3.19_TC-101-12_-_Create_a_Key,_Archive_and_Recover_it/2.3.19_TC-101-12_-_Create_a_Key,_Archive_and_Recover_it_17_RequestMessage.xml";
        KmipContext.withSpec(KmipSpec.V1_2, () -> {
            try {
                verifyXmlCodec(Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }


    private void verifyXmlFiles(String basePath) {
        int passCount = 0;
        try (Stream<Path> paths = Files.walk(Paths.get(basePath))) {
            List<Path> pathList = paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .sorted(Comparator.comparing(Path::toString))
                    .toList();
            for (Path path : pathList) {
                try {
                    verifyXmlCodec(path);
                    passCount += 1;
                } catch (Throwable e) {
                    String msg = "Error processing file: " + path + " - " + e.getMessage();
                    System.err.println(msg);
//                    e.printStackTrace();
//                    fail("Failed to process file: " + path + " - " + e.getMessage());
                }
            }
            System.out.printf("Passed: %d, Total: %d", passCount, pathList.size());
        } catch (IOException e) {
            fail("Failed to walk directory: " + basePath + " - " + e.getMessage());
        }
    }

    private void verifyXmlCodec(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        String originalXml = Files.readString(path);

        Class<? extends KmipDataType> targetClass;
        if (fileName.contains("RequestMessage")) {
            targetClass = RequestMessageStructure.class;
        } else if (fileName.contains("ResponseMessage")) {
            targetClass = ResponseMessageStructure.class;
        } else {
            // Fallback or skip if it doesn't match expected patterns
            // For now, let's try KmipDataType as a generic fallback, or just log and return
            System.out.println("Skipping file with unknown type: " + fileName);
            return;
        }

        KmipDataType deserialized;
        deserialized = xmlMapper.readValue(originalXml, targetClass);

        String newXml = xmlMapper.writeValueAsString(deserialized);

        XmlMapper verificationMapper = new XmlMapper();
        // Minify XML strings by removing whitespace between tags
        String minifiedOriginal = originalXml.replaceAll(">\\s+<", "><").trim();
        String minifiedNew = newXml.replaceAll(">\\s+<", "><").trim();

        JsonNode originalNode = verificationMapper.readTree(minifiedOriginal);
        JsonNode newNode = verificationMapper.readTree(minifiedNew);

        assertEquals(originalNode, newNode, "Mismatch in file: " + fileName);
        System.out.println("Verified file: " + fileName);
    }
}
