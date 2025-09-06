package org.purpleBean.kmip.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Utility class for testing serialization and deserialization of KMIP objects.
 * Provides reusable patterns for round-trip testing and validation.
 */
public final class SerializationTestUtils {

    private SerializationTestUtils() {
        // Utility class - prevent instantiation
    }

    /**
     * Performs a JSON round-trip test: serialize to JSON, then deserialize back to object.
     *
     * @param mapper the ObjectMapper to use
     * @param original the original object to test
     * @param clazz the class type for deserialization
     * @param <T> the type of object being tested
     * @return the deserialized object for further assertions
     */
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

    /**
     * Performs an XML round-trip test: serialize to XML, then deserialize back to object.
     *
     * @param mapper the XmlMapper to use
     * @param original the original object to test
     * @param clazz the class type for deserialization
     * @param <T> the type of object being tested
     * @return the deserialized object for further assertions
     */
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

    /**
     * Performs both JSON and XML round-trip tests.
     *
     * @param jsonMapper the ObjectMapper for JSON testing
     * @param xmlMapper the XmlMapper for XML testing
     * @param original the original object to test
     * @param clazz the class type for deserialization
     * @param <T> the type of object being tested
     */
    public static <T> void performBothRoundTrips(ObjectMapper jsonMapper, XmlMapper xmlMapper, 
                                                T original, Class<T> clazz) {
        performJsonRoundTrip(jsonMapper, original, clazz);
        performXmlRoundTrip(xmlMapper, original, clazz);
    }

    /**
     * Tests JSON serialization with custom validation.
     *
     * @param mapper the ObjectMapper to use
     * @param original the original object to serialize
     * @param jsonValidator custom validation logic for the JSON string
     * @param <T> the type of object being tested
     */
    public static <T> void testJsonSerialization(ObjectMapper mapper, T original, 
                                                Consumer<String> jsonValidator) {
        try {
            String json = mapper.writeValueAsString(original);
            jsonValidator.accept(json);
        } catch (JsonProcessingException e) {
            throw new AssertionError("JSON serialization failed", e);
        }
    }

    /**
     * Tests XML serialization with custom validation.
     *
     * @param mapper the XmlMapper to use
     * @param original the original object to serialize
     * @param xmlValidator custom validation logic for the XML string
     * @param <T> the type of object being tested
     */
    public static <T> void testXmlSerialization(XmlMapper mapper, T original, 
                                               Consumer<String> xmlValidator) {
        try {
            String xml = mapper.writeValueAsString(original);
            xmlValidator.accept(xml);
        } catch (JsonProcessingException e) {
            throw new AssertionError("XML serialization failed", e);
        }
    }

    /**
     * Tests JSON deserialization from a string.
     *
     * @param mapper the ObjectMapper to use
     * @param json the JSON string to deserialize
     * @param clazz the class type for deserialization
     * @param <T> the type of object being tested
     * @return the deserialized object
     */
    public static <T> T testJsonDeserialization(ObjectMapper mapper, String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new AssertionError("JSON deserialization failed", e);
        }
    }

    /**
     * Tests XML deserialization from a string.
     *
     * @param mapper the XmlMapper to use
     * @param xml the XML string to deserialize
     * @param clazz the class type for deserialization
     * @param <T> the type of object being tested
     * @return the deserialized object
     */
    public static <T> T testXmlDeserialization(XmlMapper mapper, String xml, Class<T> clazz) {
        try {
            return mapper.readValue(xml, clazz);
        } catch (IOException e) {
            throw new AssertionError("XML deserialization failed", e);
        }
    }

    /**
     * Validates that serialization produces expected JSON structure.
     *
     * @param json the JSON string to validate
     * @param expectedFields the fields that should be present in the JSON
     */
    public static void validateJsonStructure(String json, String... expectedFields) {
        Assertions.assertThat(json).isNotNull().isNotEmpty();
        for (String field : expectedFields) {
            Assertions.assertThat(json).contains("\"" + field + "\"");
        }
    }

    /**
     * Validates that serialization produces expected XML structure.
     *
     * @param xml the XML string to validate
     * @param expectedElements the elements that should be present in the XML
     */
    public static void validateXmlStructure(String xml, String... expectedElements) {
        Assertions.assertThat(xml).isNotNull().isNotEmpty();
        for (String element : expectedElements) {
            Assertions.assertThat(xml).contains("<" + element + ">");
        }
    }

    /**
     * Tests that serialization handles null values appropriately.
     *
     * @param mapper the ObjectMapper to use
     * @param objectWithNulls an object containing null values
     * @param clazz the class type
     * @param <T> the type of object being tested
     */
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
