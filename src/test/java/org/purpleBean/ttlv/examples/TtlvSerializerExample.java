package org.purpleBean.ttlv.examples;

import org.purpleBean.EncodingType;
import org.purpleBean.ttlv.TtlvObject;
import org.purpleBean.ttlv.serializer.TtlvObjectSerializer;

import java.util.Arrays;

/**
 * Example demonstrating how to use TtlvObjectSerializer.
 * Shows various serialization scenarios with different data types.
 */
public class TtlvSerializerExample {

    public static void main(String[] args) {
        TtlvObjectSerializer serializer = new TtlvObjectSerializer();

        System.out.println("=== TTLV Object Serialization Examples ===");

        // Example 1: Integer value (4 bytes)
        demonstrateIntegerSerialization(serializer);

        // Example 2: Text string (variable length)
        demonstrateTextStringSerialization(serializer);

        // Example 3: Boolean value (8 bytes)
        demonstrateBooleanSerialization(serializer);

        // Example 4: Multiple objects
        demonstrateMultipleObjectSerialization(serializer);

        // Example 5: Edge cases
        demonstrateEdgeCases(serializer);
    }

    private static void demonstrateIntegerSerialization(TtlvObjectSerializer serializer) {
        System.out.println("\n--- Integer Serialization Example ---");

        // Create an integer TTLV object
        byte[] tag = {0x42, 0x00, 0x01}; // Tag for "Object Type"
        byte type = EncodingType.INTEGER.getTypeValue();
        byte[] value = {0x00, 0x00, 0x00, 0x01}; // Value: 1

        TtlvObject ttlvObject = new TtlvObject(tag, type, value);

        // Serialize
        byte[] serialized = serializer.serialize(ttlvObject);

        System.out.println("Original TTLVObject: " + ttlvObject);
        System.out.println("Serialized length: " + serialized.length + " bytes (padded to multiple of 8)");
        System.out.println("Serialized bytes: " + Arrays.toString(serialized));
        System.out.println("Is properly padded: " + TtlvObjectSerializer.isProperlyPadded(serialized.length));
    }

    private static void demonstrateTextStringSerialization(TtlvObjectSerializer serializer) {
        System.out.println("\n--- Text String Serialization Example ---");

        // Create a text string TTLV object
        byte[] tag = {0x42, 0x00, 0x05}; // Tag for "Name"
        byte type = EncodingType.TEXT_STRING.getTypeValue();
        String text = "Hello, KMIP!";
        byte[] value = text.getBytes();

        TtlvObject ttlvObject = new TtlvObject(tag, type, value);

        // Serialize
        byte[] serialized = serializer.serialize(ttlvObject);

        System.out.println("Original text: '" + text + "'");
        System.out.println("Serialized length: " + serialized.length + " bytes (padded to multiple of 8)");
        System.out.println("Is properly padded: " + TtlvObjectSerializer.isProperlyPadded(serialized.length));
    }

    private static void demonstrateBooleanSerialization(TtlvObjectSerializer serializer) {
        System.out.println("\n--- Boolean Serialization Example ---");

        // Create a boolean TTLV object
        byte[] tag = {0x42, 0x00, 0x06}; // Tag for "Boolean"
        byte type = EncodingType.BOOLEAN.getTypeValue();
        byte[] value = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01}; // true

        TtlvObject ttlvObject = new TtlvObject(tag, type, value);

        // Serialize
        byte[] serialized = serializer.serialize(ttlvObject);

        System.out.println("Boolean value: true");
        System.out.println("Serialized length: " + serialized.length + " bytes (padded to multiple of 8)");
        System.out.println("Is properly padded: " + TtlvObjectSerializer.isProperlyPadded(serialized.length));
    }

    private static void demonstrateMultipleObjectSerialization(TtlvObjectSerializer serializer) {
        System.out.println("\n--- Multiple Object Serialization Example ---");

        // Create multiple TTLV objects
        TtlvObject obj1 = new TtlvObject(
                new byte[]{0x42, 0x00, 0x01},
                EncodingType.INTEGER.getTypeValue(),
                new byte[]{0x00, 0x00, 0x00, 0x01}
        );

        TtlvObject obj2 = new TtlvObject(
                new byte[]{0x42, 0x00, 0x05},
                EncodingType.TEXT_STRING.getTypeValue(),
                "Test".getBytes()
        );

        TtlvObject obj3 = new TtlvObject(
                new byte[]{0x42, 0x00, 0x06},
                EncodingType.BOOLEAN.getTypeValue(),
                new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}
        );

        // Serialize multiple objects
        byte[] serialized = serializer.serializeMultiple(obj1, obj2, obj3);

        System.out.println("Serialized " + 3 + " objects");
        System.out.println("Total serialized length: " + serialized.length + " bytes");
        System.out.println("Is properly padded: " + TtlvObjectSerializer.isProperlyPadded(serialized.length));
    }

    private static void demonstrateEdgeCases(TtlvObjectSerializer serializer) {
        System.out.println("\n--- Edge Cases Example ---");

        // Empty value
        TtlvObject emptyValue = new TtlvObject(
                new byte[]{0x42, 0x00, 0x01},
                EncodingType.STRUCTURE.getTypeValue(),
                new byte[0]
        );

        byte[] serializedEmpty = serializer.serialize(emptyValue);
        System.out.println("Empty value - Serialized length: " + serializedEmpty.length + " bytes");

        // Single byte value (needs padding)
        TtlvObject singleByte = new TtlvObject(
                new byte[]{0x42, 0x00, 0x01},
                EncodingType.BYTE_STRING.getTypeValue(),
                new byte[]{0x42}
        );

        byte[] serializedSingle = serializer.serialize(singleByte);
        System.out.println("Single byte value - Serialized length: " + serializedSingle.length + " bytes");

        // Large value
        byte[] largeValue = new byte[1000];
        Arrays.fill(largeValue, (byte) 0x41); // Fill with 'A'
        TtlvObject largeObject = new TtlvObject(
                new byte[]{0x42, 0x00, 0x01},
                EncodingType.BYTE_STRING.getTypeValue(),
                largeValue
        );

        byte[] serializedLarge = serializer.serialize(largeObject);
        System.out.println("Large value (1000 bytes) - Serialized length: " + serializedLarge.length + " bytes");

        System.out.println("All serialized objects are properly padded: " +
                (TtlvObjectSerializer.isProperlyPadded(serializedEmpty.length) &&
                        TtlvObjectSerializer.isProperlyPadded(serializedSingle.length) &&
                        TtlvObjectSerializer.isProperlyPadded(serializedLarge.length)));
    }
}