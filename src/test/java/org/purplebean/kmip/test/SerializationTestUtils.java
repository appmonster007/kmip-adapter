package org.purplebean.kmip.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.function.Consumer;
import org.assertj.core.api.Assertions;

/**
 * Utility class for testing serialization and deserialization of KMIP objects. Provides reusable
 * patterns for round-trip testing and validation.
 */
public final class SerializationTestUtils {

  private SerializationTestUtils() {
    // Utility class - prevent instantiation
  }

  /**
   * Serializes {@code original} to JSON, deserializes it back, and asserts equality.
   */
  public static <T> T performJsonRoundTrip(ObjectMapper mapper, T original, Class<T> clazz) {
    try {
      String json = mapper.writeValueAsString(original);
      T restored = mapper.readValue(json, clazz);
      Assertions
          .assertThat(restored)
          .isEqualTo(original);
      return restored;
    } catch (JsonProcessingException e) {
      throw new AssertionError("JSON round-trip failed", e);
    }
  }

  /**
   * Serializes {@code original} to XML, deserializes it back, and asserts equality.
   */
  public static <T> T performXmlRoundTrip(XmlMapper mapper, T original, Class<T> clazz) {
    try {
      String xml = mapper.writeValueAsString(original);
      T restored = mapper.readValue(xml, clazz);
      Assertions
          .assertThat(restored)
          .isEqualTo(original);
      return restored;
    } catch (JsonProcessingException e) {
      throw new AssertionError("XML round-trip failed", e);
    }
  }

  /**
   * Performs both {@link #performJsonRoundTrip} and {@link #performXmlRoundTrip} for
   * {@code original}.
   */
  public static <T> void performBothRoundTrips(
      ObjectMapper jsonMapper, XmlMapper xmlMapper, T original, Class<T> clazz) {
    performJsonRoundTrip(jsonMapper, original, clazz);
    performXmlRoundTrip(xmlMapper, original, clazz);
  }

  /**
   * Serializes {@code original} to JSON and passes the result to {@code jsonValidator}.
   */
  public static <T> void testJsonSerialization(
      ObjectMapper mapper, T original, Consumer<String> jsonValidator) {
    try {
      String json = mapper.writeValueAsString(original);
      jsonValidator.accept(json);
    } catch (JsonProcessingException e) {
      throw new AssertionError("JSON serialization failed", e);
    }
  }

  /**
   * Serializes {@code original} to XML and passes the result to {@code xmlValidator}.
   */
  public static <T> void testXmlSerialization(
      XmlMapper mapper, T original, Consumer<String> xmlValidator) {
    try {
      String xml = mapper.writeValueAsString(original);
      xmlValidator.accept(xml);
    } catch (JsonProcessingException e) {
      throw new AssertionError("XML serialization failed", e);
    }
  }

  /**
   * Deserializes {@code json} into an instance of {@code clazz}.
   */
  public static <T> T testJsonDeserialization(ObjectMapper mapper, String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new AssertionError("JSON deserialization failed", e);
    }
  }

  /**
   * Deserializes {@code xml} into an instance of {@code clazz}.
   */
  public static <T> T testXmlDeserialization(XmlMapper mapper, String xml, Class<T> clazz) {
    try {
      return mapper.readValue(xml, clazz);
    } catch (IOException e) {
      throw new AssertionError("XML deserialization failed", e);
    }
  }

  /**
   * Asserts that {@code json} is non-empty and contains each of {@code expectedFields}.
   */
  public static void validateJsonStructure(String json, String... expectedFields) {
    Assertions
        .assertThat(json)
        .isNotNull()
        .isNotEmpty();
    for (String field : expectedFields) {
      Assertions
          .assertThat(json)
          .contains("\"" + field + "\"");
    }
  }

  /**
   * Asserts that {@code xml} is non-empty and contains each of {@code expectedElements}.
   */
  public static void validateXmlStructure(String xml, String... expectedElements) {
    Assertions
        .assertThat(xml)
        .isNotNull()
        .isNotEmpty();
    for (String element : expectedElements) {
      Assertions
          .assertThat(xml)
          .contains("<" + element + ">");
    }
  }

  /**
   * Round-trips {@code objectWithNulls} through JSON and asserts the result is non-null.
   */
  public static <T> void testNullHandling(ObjectMapper mapper, T objectWithNulls, Class<T> clazz) {
    try {
      String serialized = mapper.writeValueAsString(objectWithNulls);
      T deserialized = mapper.readValue(serialized, clazz);
      Assertions
          .assertThat(deserialized)
          .isNotNull();
    } catch (JsonProcessingException e) {
      throw new AssertionError("Null handling test failed", e);
    }
  }
}
