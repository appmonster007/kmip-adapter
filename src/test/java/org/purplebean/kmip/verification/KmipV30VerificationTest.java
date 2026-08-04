package org.purplebean.kmip.verification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipMaskType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestMessageStructure;
import org.purplebean.kmip.api.response.ResponseMessageStructure;
import org.purplebean.kmip.codec.KmipCodecManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * KMIP 3.0 verification tests. Mirrors {@link KmipV21VerificationTest}:
 * walks the OASIS profile test-case XML files, extracts every
 * {@code <RequestMessage>}/{@code <ResponseMessage>}, round-trips it through
 * the codec, and asserts the re-serialized XML is semantically equivalent
 * to the original.
 *
 * <p>The KMIP 3.0 profile splits test cases into {@code mandatory/} and
 * {@code optional/} subfolders (unlike the flat 2.1 layout), so a dedicated
 * test method is provided for each set in addition to a combined pass.
 */
public class KmipV30VerificationTest {

  private static final String FIXED_TIMESTAMP = "1970-01-01T00:00:00+00:00";
  private static final String FIXED_CORRELATION = "FIXED_CORRELATION_VALUE";
  private static final String FIXED_HEX_16 = "aabbccdd00112233aabbccdd00112233";
  private static final String FIXED_HEX_4 = "aabbccdd";

  private static final String TEST_CASES_ROOT =
      "/docs/kmip-spec/v3.x/kmip-testcases/v3.0/csd01/test-cases/kmip-v3.0";

  private final XmlMapper xmlMapper = KmipCodecManager.getXmlMapper();
  private final String projectRoot = System.getProperty("user.dir");

  // ---------------------------------------------------------------------
  // Single-file smoke test
  // ---------------------------------------------------------------------

  @DisplayName("Test KMIP 3.0 Specific File")
  @Test
  public void testSpecificFile() {
    String filePath = projectRoot + TEST_CASES_ROOT + "/mandatory/AKLC-M-1-30.xml";
    List<String> failures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
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

  // ---------------------------------------------------------------------
  // Diagnostics
  // ---------------------------------------------------------------------

  @DisplayName("Diagnose specific failing files")
  @Test
  public void diagnoseTargetedFiles() {
    String base = projectRoot + TEST_CASES_ROOT;
    // Populate with failing files once the mandatory/optional sweeps expose them.
    String[] targets = new String[] {
        // "mandatory/AKLC-M-1-30.xml",
        // "optional/AKLC-O-1-30.xml",
    };
    List<String> allFailures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
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

  @DisplayName("Diagnose first 10 KMIP 3.0 test case files")
  @Test
  public void diagnoseFirst10() {
    String base = projectRoot + TEST_CASES_ROOT;
    List<String> allFailures = new java.util.ArrayList<>();
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
      try (Stream<Path> s = Files.walk(Paths.get(base))) {
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
              // print top project frames (skip java.base, jackson, jupiter)
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

  // ---------------------------------------------------------------------
  // Bulk verification
  // ---------------------------------------------------------------------

  @DisplayName("Test KMIP 3.0 Mandatory Test Cases")
  @Test
  public void testKmip30MandatoryTestCases() {
    String basePath = projectRoot + TEST_CASES_ROOT + "/mandatory";
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
      verifyXmlFiles(basePath, "mandatory");
      return null;
    });
  }

  @DisplayName("Test KMIP 3.0 Optional Test Cases")
  @Test
  public void testKmip30OptionalTestCases() {
    String basePath = projectRoot + TEST_CASES_ROOT + "/optional";
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
      verifyXmlFiles(basePath, "optional");
      return null;
    });
  }

  @DisplayName("Test KMIP 3.0 Test Cases (mandatory + optional)")
  @Test
  public void testKmip30TestCases() {
    String basePath = projectRoot + TEST_CASES_ROOT;
    KmipContext.withSpec(KmipSpec.V3_0, () -> {
      verifyXmlFiles(basePath, "all");
      return null;
    });
  }

  private void verifyXmlFiles(String basePath, String label) {
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
      System.out.printf("[%s] Passed: %d / %d messages%n", label, passCount, totalMessages);
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
        .replaceAll("\\$DIGEST_VALUE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$AUTHENTICATED_ENCRYPTION_TAG(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$IV_COUNTER_NONCE(?:_\\d+)?", FIXED_HEX_16)
        .replaceAll("\\$MAC_DATA(?:_\\d+)?", FIXED_HEX_16)
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

    JsonNode originalNode = normalizeNonSemanticRepresentation(verificationMapper.readTree(minifiedOriginal));
    JsonNode newNode = normalizeNonSemanticRepresentation(verificationMapper.readTree(minifiedNew));

    assertEquals(originalNode, newNode, "Round-trip mismatch in " + fileName + " <" + tag + ">");
  }

  /**
   * Normalizes wire-representation choices the KMIP spec does not assign semantic meaning to,
   * applied identically to both sides of the comparison so it cannot mask a real asymmetric bug:
   * <ul>
   *   <li>Bitmask flag-name ordering (e.g. CryptographicUsageMask's "Decrypt Encrypt") - re-parses
   *       via the field's own {@link KmipMaskType#getFromMaskString} and re-renders via
   *       {@link KmipMaskType#getMaskString()}, so the canonical form is exactly what this
   *       codebase's own mask type (e.g. CryptographicUsageMask.MaskEnum#toMaskString, already
   *       fixed to sort ascending by bit value) would produce - not an independently-invented
   *       test-side convention. The OASIS v3.0 corpus itself is internally inconsistent about
   *       flag order (both orderings appear for the identical bitmask, occasionally within the
   *       same file), so this cannot be satisfied by any single serialization choice.</li>
   *   <li>ByteString hex text case - a byte's value is unaffected by whether its hex digits are
   *       rendered upper or lower case.</li>
   * </ul>
   */
  private JsonNode normalizeNonSemanticRepresentation(JsonNode node) {
    if (node.isObject()) {
      ObjectNode obj = (ObjectNode) node;
      obj.fields().forEachRemaining(entry -> normalizeField(entry.getKey(), entry.getValue()));
    } else if (node.isArray()) {
      for (JsonNode child : node) {
        normalizeNonSemanticRepresentation(child);
      }
    }
    return node;
  }

  private void normalizeField(String fieldName, JsonNode child) {
    if (child.isObject()) {
      ObjectNode childObj = (ObjectNode) child;
      JsonNode typeNode = childObj.get("type");
      JsonNode valueNode = childObj.get("value");
      if (typeNode != null && valueNode != null && valueNode.isTextual()) {
        String type = typeNode.asText();
        String value = valueNode.asText();
        if ("Integer".equals(type) && value.contains(" ")) {
          canonicalizeMaskString(fieldName, value).ifPresent(v -> childObj.put("value", v));
        } else if ("ByteString".equals(type)) {
          childObj.put("value", value.toLowerCase(Locale.ROOT));
        }
      }
    }
    normalizeNonSemanticRepresentation(child);
  }

  private Optional<String> canonicalizeMaskString(String fieldName, String value) {
    KmipTag.Value tag;
    try {
      tag = KmipTag.fromName(fieldName);
    } catch (RuntimeException e) {
      return Optional.empty();
    }
    var fromMaskString = KmipMaskType.getFromMaskString(tag);
    if (fromMaskString == null) {
      return Optional.empty();
    }
    return Optional.of(fromMaskString.apply(value).getMaskString());
  }
}
