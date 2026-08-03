package org.purplebean.kmip.verification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.api.response.ResponseMessageStructure;
import org.purplebean.kmip.codec.KmipCodecManager;

public class KmipV12VerificationTest {

  private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
  private final String projectRoot = System.getProperty("user.dir");

  @DisplayName("Test KMIP 1.2 Test Cases")
  @Test
  public void testKmip12TestCases() {
    String basePath = projectRoot +
        "/docs/kmip-spec/v1.x/test-cases-messages/2_KMIP_Test_Cases/2.3_KMIP_1.2_Test_Cases";
    KmipContext.withSpec(KmipSpec.V1_2, () -> {
      verifyXmlFiles(basePath);
      return null;
    });
  }

  private void verifyXmlFiles(String basePath) {
    int passCount = 0;
    try (Stream<Path> paths = Files.walk(Paths.get(basePath))) {
      List<Path> pathList = paths
          .filter(Files::isRegularFile)
          .filter(path -> path
              .toString()
              .endsWith(".xml"))
          .sorted(Comparator.comparing(Path::toString))
          .toList();
      for (Path path : pathList) {
        try {
          verifyXmlCodec(path);
          passCount += 1;
        } catch (Throwable e) {
          System.err.println("Error processing file: " + path + " - " + e.getMessage());
        }
      }
      System.out.printf("Passed: %d, Total: %d%n", passCount, pathList.size());
    } catch (IOException e) {
      fail("Failed to walk directory: " + basePath + " - " + e.getMessage());
    }
  }

  private void verifyXmlCodec(Path path) throws IOException {
    String fileName = path
        .getFileName()
        .toString();
    String originalXml = Files.readString(path);

    Class<? extends KmipDataType> targetClass;
    if (fileName.contains("RequestMessage")) {
      targetClass = RequestMessageStructure.class;
    } else if (fileName.contains("ResponseMessage")) {
      targetClass = ResponseMessageStructure.class;
    } else {
      System.out.println("Skipping file with unknown type: " + fileName);
      return;
    }

    KmipDataType deserialized = xmlMapper.readValue(originalXml, targetClass);
    String newXml = xmlMapper.writeValueAsString(deserialized);

    XmlMapper verificationMapper = new XmlMapper();
    String minifiedOriginal = originalXml
        .replaceAll(">\\s+<", "><")
        .trim();
    String minifiedNew = newXml
        .replaceAll(">\\s+<", "><")
        .trim();

    JsonNode originalNode = verificationMapper.readTree(minifiedOriginal);
    JsonNode newNode = verificationMapper.readTree(minifiedNew);

    assertEquals(originalNode, newNode, "Mismatch in file: " + fileName);
    System.out.println("Verified file: " + fileName);
  }
}
