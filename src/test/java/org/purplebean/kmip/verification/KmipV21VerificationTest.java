package org.purplebean.kmip.verification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.api.response.ResponseMessageStructure;
import org.purplebean.kmip.codec.KmipCodecManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class KmipV21VerificationTest {

  private static final String FIXED_TIMESTAMP = "1970-01-01T00:00:00+00:00";
  private static final String FIXED_CORRELATION = "FIXED_CORRELATION_VALUE";
  private static final String FIXED_HEX_16 = "aabbccdd00112233aabbccdd00112233";
  private static final String FIXED_HEX_4 = "aabbccdd";
  private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
  private final String projectRoot = System.getProperty("user.dir");

  @DisplayName("Test KMIP 2.1 Specific File")
  @Disabled("Duplicate of testKmip21TestCases; kept as a debug entry point, not run by default")
  @Test
  public void testSpecificFile() {
    String filePath = projectRoot +
        "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1/TC-ASYNC-1-21.xml";
    List<String> failures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V2_1, () -> {
      try {
        int[] counts = verifyXmlFile(Paths.get(filePath), failures);
        System.out.printf("Passed: %d / %d messages%n", counts[0], counts[1]);
      } catch (Exception e) {
        failures.add("Error: " + e.getMessage());
      }
      return null;
    });
    if (!failures.isEmpty()) {
      fail(failures.size() + " verification failure(s):\n" + String.join("\n", failures));
    }
  }

  @DisplayName("Diagnose specific failing files")
  @Disabled("Duplicate of testKmip21TestCases; kept as a debug entry point, not run by default")
  @Test
  public void diagnoseTargetedFiles() {
    String base =
        projectRoot + "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1";
    String[] targets = {
        "TC-STREAM-MAC-1-21.xml",
        "TC-PKCS12-2-21.xml",
        "TC-PKCS12-1-21.xml",
        "TC-REENCRYPT-1-21.xml",
        "TC-REENCRYPT-2-21.xml",
        "TC-REENCRYPT-3-21.xml",
        "TC-REENCRYPT-4-21.xml",
        "TC-REENCRYPT-5-21.xml",
        "TC-REENCRYPT-6-21.xml",
        "TC-WRAP-1-21.xml",
        "TC-WRAP-2-21.xml",
        "TC-WRAP-3-21.xml",
        "TC-RNG-ATTR-1-21.xml",
        "TC-RNG-ATTR-2-21.xml",
        "TC-REKEY-5-21.xml",
        "TC-REKEY-6-21.xml",
        "TC-REKEY-7-21.xml",
        "TC-REKEY-8-21.xml",
        "TC-ECC-3-21.xml",
        "TC-ECDSA-SIGN-1-21.xml",
        "TC-ECDSA-SIGN-DIGESTEDDATA-1-21.xml",
        "TC-RSA-SIGN-DIGESTEDDATA-1-21.xml",
        "TC-PGP-1-21.xml",
        "TC-DERIVEKEY-3-21.xml",
        "TC-DERIVEKEY-4-21.xml",
        "TC-DERIVEKEY-5-21.xml",
        "TC-IMPEXP-2-21.xml",
        "TC-IMPEXP-3-21.xml",
        "TC-IMPEXP-4-21.xml",
        "TC-IMPEXP-5-21.xml",
        "TC-MD-21-21.xml",
        "TC-MD-22-21.xml",
        "TC-MD-23-21.xml",
        "TC-MD-24-21.xml"
    };
    List<String> allFailures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V2_1, () -> {
      for (String name : targets) {
        Path p = Paths.get(base, name);
        if (!Files.exists(p)) {
          System.err.println("Missing: " + name);
          continue;
        }
        System.out.println("\n>>> FILE: " + name);
        allFailures.addAll(diagnoseFile(p));
      }
      return null;
    });
  }

  @DisplayName("Diagnose first 10 KMIP 2.1 test case files")
  @Disabled("Duplicate of testKmip21TestCases; kept as a debug entry point, not run by default")
  @Test
  public void diagnoseFirst10() {
    String base =
        projectRoot + "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1";
    List<String> allFailures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V2_1, () -> {
      try (java.util.stream.Stream<Path> s = Files.walk(Paths.get(base))) {
        List<Path> files = s
            .filter(Files::isRegularFile)
            .filter(p -> p
                .toString()
                .endsWith(".xml"))
            .sorted(Comparator.comparing(Path::toString))
            .limit(10)
            .toList();
        for (Path path : files) {
          System.out.println("\n>>> FILE: " + path.getFileName());
          allFailures.addAll(diagnoseFile(path));
        }
      } catch (IOException e) {
        fail(e.getMessage());
      }
      return null;
    });
    if (!allFailures.isEmpty()) {
      fail(allFailures.size() + " verification failure(s):\n" + String.join("\n", allFailures));
    }
  }

  private List<String> diagnoseFile(Path path) {
    List<String> failures = new java.util.ArrayList<>();
    try {
      String rawXml = Files.readString(path);
      String xml = replacePlaceholders(rawXml);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      Document doc = factory
          .newDocumentBuilder()
          .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
      Transformer transformer = TransformerFactory
          .newInstance()
          .newTransformer();
      for (String tag : new String[] {"RequestMessage", "ResponseMessage"}) {
        NodeList nodes = doc
            .getDocumentElement()
            .getElementsByTagName(tag);
        for (int i = 0; i < nodes.getLength(); i++) {
          StringWriter sw = new StringWriter();
          transformer.transform(new DOMSource((Element) nodes.item(i)), new StreamResult(sw));
          String fragment = sw.toString();
          try {
            // Also print batch item errors for diagnostic path.
            Class<? extends KmipDataType> targetCls = tag.equals("RequestMessage")
                ? RequestMessageStructure.class
                : ResponseMessageStructure.class;
            KmipDataType deserialized = xmlMapper.readValue(fragment, targetCls);
            List<? extends Exception> batchErrors = List.of();
            if (deserialized instanceof RequestMessageStructure rm) {
              batchErrors = rm.getRequestBatchItemErrors();
            } else if (deserialized instanceof ResponseMessageStructure rm) {
              batchErrors = rm.getResponseBatchItemErrors();
            }
            for (int j = 0; j < batchErrors.size(); j++) {
              Exception be = batchErrors.get(j);
              if (be != null) {
                System.err.printf("  BATCH-ITEM-ERR %s %s[%d].batch[%d]: %s: %s%n",
                    path.getFileName(), tag, i, j,
                    be
                        .getClass()
                        .getSimpleName(), be.getMessage());
                Throwable cur = be;
                int depth = 0;
                while (cur != null && depth < 4) {
                  for (StackTraceElement fr : cur.getStackTrace()) {
                    if (fr
                        .getClassName()
                        .startsWith("org.purplebean")) {
                      System.err.printf("      at %s%n", fr);
                      break;
                    }
                  }
                  cur = cur.getCause();
                  depth++;
                }
              }
            }
            verifyMessageFragment(fragment, tag, path
                .getFileName()
                .toString());
          } catch (Throwable e) {
            String msg = String.format("FAIL %s %s[%d]: [%s] %s",
                path.getFileName(), tag, i, e
                    .getClass()
                    .getSimpleName(), e.getMessage());
            failures.add(msg);
            System.err.println("  " + msg);
            Throwable cur = e;
            int depth = 0;
            while (cur != null && depth < 8) {
              System.err.printf("    [%d] %s: %s%n", depth, cur
                  .getClass()
                  .getSimpleName(), cur.getMessage());
              StackTraceElement[] st = cur.getStackTrace();
              int printed = 0;
              for (StackTraceElement fr : st) {
                String cn = fr.getClassName();
                if (cn.startsWith("org.purplebean")) {
                  System.err.printf("        at %s%n", fr);
                  if (++printed >= 8) {
                    break;
                  }
                }
              }
              cur = cur.getCause();
              depth++;
            }
          }
        }
      }
    } catch (Throwable e) {
      String msg = "PARSE ERROR " + path.getFileName() + ": " + e.getMessage();
      failures.add(msg);
      System.err.println("  " + msg);
    }
    return failures;
  }

  @DisplayName("Test KMIP 2.1 Test Cases")
  @Test
  public void testKmip21TestCases() {
    String basePath =
        projectRoot + "/docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1";
    KmipContext.withSpec(KmipSpec.V2_1, () -> {
      verifyXmlFiles(basePath);
      return null;
    });
  }

  private void verifyXmlFiles(String basePath) {
    int passCount = 0;
    int totalMessages = 0;
    List<String> failures = new java.util.ArrayList<>();
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
          int[] counts = verifyXmlFile(path, failures);
          passCount += counts[0];
          totalMessages += counts[1];
        } catch (Throwable e) {
          String msg = "Error processing file: " + path.getFileName() + " - " + e.getMessage();
          failures.add(msg);
          System.err.println(msg);
        }
      }
      System.out.printf("Passed: %d / %d messages%n", passCount, totalMessages);
    } catch (IOException e) {
      fail("Failed to walk directory: " + basePath + " - " + e.getMessage());
    }
    if (!failures.isEmpty()) {
      fail(failures.size() + " verification failure(s):\n" + String.join("\n", failures));
    }
  }

  // Returns [passCount, totalCount] for messages in the file
  private int[] verifyXmlFile(Path path, List<String> failures) throws Exception {
    String rawXml = Files.readString(path);
    // Replace dynamic placeholders with stable values so DateTime parsing succeeds
    String xml = replacePlaceholders(rawXml);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();

    int pass = 0;
    int total = 0;

    for (String tag : new String[] {"RequestMessage", "ResponseMessage"}) {
      NodeList nodes = doc
          .getDocumentElement()
          .getElementsByTagName(tag);
      for (int i = 0; i < nodes.getLength(); i++) {
        total++;
        Element element = (Element) nodes.item(i);
        StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(element), new StreamResult(sw));
        String fragment = sw.toString();

        try {
          verifyMessageFragment(fragment, tag, path
              .getFileName()
              .toString());
          pass++;
        } catch (Throwable e) {
          String msg = String.format("[%s] %s[%d]: %s", path.getFileName(), tag, i, e.getMessage());
          failures.add(msg);
          System.err.println("  " + msg);
        }
      }
    }
    return new int[] {pass, total};
  }

  private String replacePlaceholders(String xml) {
    return xml
        // $NOW+N and $NOW-N must be replaced before plain $NOW
        .replaceAll("\\$NOW[+\\-]\\d+", FIXED_TIMESTAMP)
        .replace("$NOW", FIXED_TIMESTAMP)
        .replace("$SERVER_CORRELATION_VALUE", FIXED_CORRELATION)
        .replaceAll("\\$ASYNCHRONOUS_CORRELATION_VALUE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$KEY_MATERIAL(?:_\\d+)?", "000102030405060708090a0b0c0d0e0f")
        .replaceAll("\\$KEY_VALUE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$CORRELATION_VALUE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$DATA(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$SHORT_UNIQUE_IDENTIFIER(?:_RANDOM)?(?:_\\d+)?", FIXED_HEX_4)
        .replaceAll("\\$SIGNATURE_DATA(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$TICKET_VALUE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$UNIQUE_IDENTIFIER(?:_\\d+)?", "test-unique-id-00000000");
  }

  private void verifyMessageFragment(String fragment, String tag, String fileName)
      throws Exception {
    Class<? extends KmipDataType> targetClass = tag.equals("RequestMessage")
        ? RequestMessageStructure.class
        : ResponseMessageStructure.class;

    KmipDataType deserialized = xmlMapper.readValue(fragment, targetClass);
    String reserialized = xmlMapper.writeValueAsString(deserialized);

    XmlMapper verificationMapper = new XmlMapper();
    String minifiedOriginal = fragment
        .replaceAll(">\\s+<", "><")
        .trim();
    String minifiedNew = reserialized
        .replaceAll(">\\s+<", "><")
        .trim();

    JsonNode originalNode = verificationMapper.readTree(minifiedOriginal);
    JsonNode newNode = verificationMapper.readTree(minifiedNew);

    // Normalize semantic-equivalence differences that are not real bugs:
    //  - ByteString hex values: lowercase both sides (KMIP wire is bytes, hex case is cosmetic)
    //  - CryptographicUsageMask value: bit-flag token order is irrelevant (represents same int)
    normalizeSemanticEquivalents(originalNode);
    normalizeSemanticEquivalents(newNode);

    assertEquals(originalNode, newNode, "Round-trip mismatch in " + fileName + " <" + tag + ">");
//        System.out.println("Verified: " + fileName + " <" + tag + ">");
  }

  /**
   * Walks a Jackson JSON tree parsed from KMIP XML and normalizes semantically-equivalent
   * differences that should not fail round-trip tests:
   *
   * <ul>
   *   <li>ByteString {@code value} strings are lowercased (spec allows either case).</li>
   *   <li>Integer {@code value} strings for CryptographicUsageMask are token-sorted
   *       (bit-flag order is not observable on the wire — the wire type is a single Integer).</li>
   * </ul>
   */
  private void normalizeSemanticEquivalents(JsonNode node) {
    normalizeSemanticEquivalents(node, null);
  }

  private void normalizeSemanticEquivalents(JsonNode node, String parentField) {
    if (node == null || node.isNull()) {
      return;
    }
    if (node.isObject()) {
      com.fasterxml.jackson.databind.node.ObjectNode obj =
          (com.fasterxml.jackson.databind.node.ObjectNode) node;
      JsonNode typeNode = obj.get("type");
      JsonNode valueNode = obj.get("value");
      if (typeNode != null && valueNode != null && valueNode.isTextual()) {
        String type = typeNode.asText();
        String value = valueNode.asText();
        if ("ByteString".equals(type)) {
          obj.put("value", value.toLowerCase(java.util.Locale.ROOT));
        } else if ("Integer".equals(type)
            && ("CryptographicUsageMask".equals(parentField)
            || "ProtectionStorageMask".equals(parentField)
            || "StorageStatusMask".equals(parentField))
            && value.contains(" ")) {
          String[] tokens = value
              .trim()
              .split("\\s+");
          java.util.Arrays.sort(tokens);
          obj.put("value", String.join(" ", tokens));
        }
      }
      java.util.Iterator<java.util.Map.Entry<String, JsonNode>> it = obj.fields();
      while (it.hasNext()) {
        java.util.Map.Entry<String, JsonNode> e = it.next();
        normalizeSemanticEquivalents(e.getValue(), e.getKey());
      }
    } else if (node.isArray()) {
      for (JsonNode child : node) {
        normalizeSemanticEquivalents(child, parentField);
      }
    }
  }
}
