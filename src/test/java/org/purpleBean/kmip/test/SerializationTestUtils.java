package org.purpleBean.kmip.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Utility class for testing serialization and deserialization of KMIP objects. Provides reusable
 * patterns for round-trip testing and validation.
 */
public final class SerializationTestUtils {

    private SerializationTestUtils() {
        // Utility class - prevent instantiation
    }

    public static <T> T performJsonRoundTrip(ObjectMapper mapper, T original, Class<T> clazz) {
        try {
            String json = mapper.writeValueAsString(original);
            T restored = mapper.readValue(json, clazz);
            Assertions.assertThat(restored).isEqualTo(original);
            return restored;
        } catch (JsonProcessingException e) {
            throw new AssertionError("JSON round-trip failed", e);
        }
    }

    public static <T> T performXmlRoundTrip(XmlMapper mapper, T original, Class<T> clazz) {
        try {
            String xml = mapper.writeValueAsString(original);
            T restored = mapper.readValue(xml, clazz);
            Assertions.assertThat(restored).isEqualTo(original);
            return restored;
        } catch (JsonProcessingException e) {
            throw new AssertionError("XML round-trip failed", e);
        }
    }

    public static <T> void performBothRoundTrips(
            ObjectMapper jsonMapper, XmlMapper xmlMapper, T original, Class<T> clazz) {
        performJsonRoundTrip(jsonMapper, original, clazz);
        performXmlRoundTrip(xmlMapper, original, clazz);
    }

    public static <T> void testJsonSerialization(
            ObjectMapper mapper, T original, Consumer<String> jsonValidator) {
        try {
            String json = mapper.writeValueAsString(original);
            jsonValidator.accept(json);
        } catch (JsonProcessingException e) {
            throw new AssertionError("JSON serialization failed", e);
        }
    }

    public static <T> void testXmlSerialization(
            XmlMapper mapper, T original, Consumer<String> xmlValidator) {
        try {
            String xml = mapper.writeValueAsString(original);
            xmlValidator.accept(xml);
        } catch (JsonProcessingException e) {
            throw new AssertionError("XML serialization failed", e);
        }
    }

    public static <T> T testJsonDeserialization(ObjectMapper mapper, String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new AssertionError("JSON deserialization failed", e);
        }
    }

    public static <T> T testXmlDeserialization(XmlMapper mapper, String xml, Class<T> clazz) {
        try {
            return mapper.readValue(xml, clazz);
        } catch (IOException e) {
            throw new AssertionError("XML deserialization failed", e);
        }
    }

    public static void validateJsonStructure(String json, String... expectedFields) {
        Assertions.assertThat(json).isNotNull().isNotEmpty();
        for (String field : expectedFields) {
            Assertions.assertThat(json).contains("\"" + field + "\"");
        }
    }

    public static void validateXmlStructure(String xml, String... expectedElements) {
        Assertions.assertThat(xml).isNotNull().isNotEmpty();
        for (String element : expectedElements) {
            Assertions.assertThat(xml).contains("<" + element + ">");
        }
    }

    public static <T> void testNullHandling(ObjectMapper mapper, T objectWithNulls, Class<T> clazz) {
        try {
            String serialized = mapper.writeValueAsString(objectWithNulls);
            T deserialized = mapper.readValue(serialized, clazz);
            Assertions.assertThat(deserialized).isNotNull();
        } catch (JsonProcessingException e) {
            throw new AssertionError("Null handling test failed", e);
        }
    }
}
