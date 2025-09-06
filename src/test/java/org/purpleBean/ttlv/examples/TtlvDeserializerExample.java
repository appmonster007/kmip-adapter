package org.purpleBean.ttlv.examples;

import org.purpleBean.EncodingType;
import org.purpleBean.ttlv.TtlvObject;
import org.purpleBean.ttlv.deserializer.TtlvObjectDeserializer;
import org.purpleBean.ttlv.serializer.TtlvObjectSerializer;

import java.util.Arrays;
import java.util.List;

/**
 * Example demonstrating how to use TtlvObjectDeserializer.
 * Shows various deserialization scenarios and round-trip testing.
 */
public class TtlvDeserializerExample {

    public static void main(String[] args) {
        TtlvObjectDeserializer deserializer = new TtlvObjectDeserializer();

        System.out.println("=== TTLV Object Deserialization Examples ===");

        // Example 1: Basic deserialization
        demonstrateBasicDeserialization(deserializer);

        // Example 2: Multiple object deserialization
        demonstrateMultipleObjectDeserialization(deserializer);

        // Example 3: Round-trip testing (serialize then deserialize)
        demonstrateRoundTripTesting(deserializer);

        // Example 4: Edge cases
        demonstrateEdgeCases(deserializer);

        // Example 5: Error handling
        demonstrateErrorHandling(deserializer);
    }

    private static void demonstrateBasicDeserialization(TtlvObjectDeserializer deserializer) {
        System.out.println("\n--- Basic Deserialization Example ---");

        // Given - serialized data with padding
        byte[] data = {
                0x42, 0x00, 0x01,  // tag for "Object Type"
                0x05,               // type (ENUMERATION)
                0x00, 0x00, 0x00, 0x04,  // length (4)
                0x00, 0x00, 0x00, 0x01,  // value (1)
                0x00, 0x00, 0x00, 0x00   // padding (4 bytes)
        };

        // When
        TtlvObject result = deserializer.deserialize(data);

        // Then
        System.out.println("Deserialized TTLVObject: " + result);
        System.out.println("Tag: " + Arrays.toString(result.getTag()));
        System.out.println("Type: " + result.getType());
        System.out.println("Length: " + result.getLength());
        System.out.println("Value: " + Arrays.toString(result.getValue()));
    }

    private static void demonstrateMultipleObjectDeserialization(TtlvObjectDeserializer deserializer) {
        System.out.println("\n--- Multiple Object Deserialization Example ---");

        // Given - serialized data containing multiple TTLV objects
        byte[] data = {
                // First object
                0x42, 0x00, 0x01,  // tag
                0x02,               // type (INTEGER)
                0x00, 0x00, 0x00, 0x04,  // length (4)
                0x00, 0x00, 0x00, 0x01,  // value (1)
                0x00, 0x00, 0x00, 0x00,  // padding
                // Second object
                0x42, 0x00, 0x05,  // tag
                0x07,               // type (TEXT_STRING)
                0x00, 0x00, 0x00, 0x04,  // length (4)
                0x54, 0x65, 0x73, 0x74,  // value ("Test")
                0x00, 0x00, 0x00, 0x00,  // padding
                // Third object
                0x42, 0x00, 0x06,  // tag
                0x06,               // type (BOOLEAN)
                0x00, 0x00, 0x00, 0x08,  // length (8)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01   // value (true) - no padding needed
        };

        // When
        List<TtlvObject> results = deserializer.deserializeMultiple(data);

        // Then
        System.out.println("Deserialized " + results.size() + " TTLVObjects:");
        for (int i = 0; i < results.size(); i++) {
            TtlvObject obj = results.get(i);
            System.out.println("  Object " + (i + 1) + ": " + obj);
        }
    }

    private static void demonstrateRoundTripTesting(TtlvObjectDeserializer deserializer) {
        System.out.println("\n--- Round-trip Testing Example ---");

        // Create original objects
        TtlvObject original1 = new TtlvObject(
                new byte[]{0x42, 0x00, 0x01},
                EncodingType.INTEGER.getTypeValue(),
                new byte[]{0x00, 0x00, 0x00, 0x42}
        );

        TtlvObject original2 = new TtlvObject(
                new byte[]{0x42, 0x00, 0x05},
                EncodingType.TEXT_STRING.getTypeValue(),
                "Hello, KMIP!".getBytes()
        );

        // Serialize using the serializer
        TtlvObjectSerializer serializer = new TtlvObjectSerializer();
        byte[] serialized = serializer.serializeMultiple(original1, original2);

        System.out.println("Serialized data length: " + serialized.length + " bytes");
        System.out.println("Is properly padded: " + TtlvObjectDeserializer.isProperlyPadded(serialized.length));

        // Deserialize back
        List<TtlvObject> deserialized = deserializer.deserializeMultiple(serialized);

        System.out.println("Round-trip successful: " + (deserialized.size() == 2));

        // Verify first object
        TtlvObject deserialized1 = deserialized.get(0);
        boolean obj1Match = Arrays.equals(original1.getTag(), deserialized1.getTag()) &&
                original1.getType() == deserialized1.getType() &&
                original1.getLength() == deserialized1.getLength() &&
                Arrays.equals(original1.getValue(), deserialized1.getValue());
        System.out.println("First object matches: " + obj1Match);

        // Verify second object
        TtlvObject deserialized2 = deserialized.get(1);
        boolean obj2Match = Arrays.equals(original2.getTag(), deserialized2.getTag()) &&
                original2.getType() == deserialized2.getType() &&
                original2.getLength() == deserialized2.getLength() &&
                Arrays.equals(original2.getValue(), deserialized2.getValue());
        System.out.println("Second object matches: " + obj2Match);
    }

    private static void demonstrateEdgeCases(TtlvObjectDeserializer deserializer) {
        System.out.println("\n--- Edge Cases Example ---");

        // Empty value
        byte[] emptyValueData = {
                0x42, 0x00, 0x01,  // tag
                0x01,               // type (STRUCTURE)
                0x00, 0x00, 0x00, 0x00   // length (0)
        };

        TtlvObject emptyValue = deserializer.deserialize(emptyValueData);
        System.out.println("Empty value object: " + emptyValue);
        System.out.println("Value length: " + emptyValue.getValue().length);

        // Single byte value with padding
        byte[] singleByteData = {
                0x42, 0x00, 0x01,  // tag
                0x08,               // type (BYTE_STRING)
                0x00, 0x00, 0x00, 0x01,  // length (1)
                0x42,               // value (1 byte)
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00  // padding (7 bytes)
        };

        TtlvObject singleByte = deserializer.deserialize(singleByteData);
        System.out.println("Single byte object: " + singleByte);
        System.out.println("Value: " + Arrays.toString(singleByte.getValue()));

        // Large value
        byte[] largeValue = new byte[1000];
        Arrays.fill(largeValue, (byte) 0x41); // Fill with 'A'

        byte[] largeData = new byte[1008]; // 8 (header) + 1000 (value) + 0 (padding)
        System.arraycopy(new byte[]{0x42, 0x00, 0x01, 0x08}, 0, largeData, 0, 4); // tag + type
        largeData[4] = (byte) 0x00;
        largeData[5] = (byte) 0x00;
        largeData[6] = (byte) 0x03;
        largeData[7] = (byte) 0xE8; // length 1000
        System.arraycopy(largeValue, 0, largeData, 8, 1000);

        TtlvObject largeObject = deserializer.deserialize(largeData);
        System.out.println("Large object length: " + largeObject.getLength());
        System.out.println("Large object value matches: " + Arrays.equals(largeValue, largeObject.getValue()));
    }

    private static void demonstrateErrorHandling(TtlvObjectDeserializer deserializer) {
        System.out.println("\n--- Error Handling Example ---");

        // Test null data
        try {
            deserializer.deserialize(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Null data error: " + e.getMessage());
        }

        // Test empty data
        try {
            deserializer.deserialize(new byte[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Empty data error: " + e.getMessage());
        }

        // Test data too short
        try {
            deserializer.deserialize(new byte[]{0x01, 0x02, 0x03, 0x04});
        } catch (IllegalArgumentException e) {
            System.out.println("Data too short error: " + e.getMessage());
        }

        // Test data not multiple of 8
        try {
            byte[] invalidData = {0x01, 0x02, 0x03, 0x04, 0x00, 0x00, 0x00, 0x00, 0x01};
            deserializer.deserialize(invalidData);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid padding error: " + e.getMessage());
        }

        // Test insufficient data for value
        try {
            byte[] insufficientData = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    0x00, 0x00, 0x00, 0x04,  // length (4)
                    0x00, 0x01,         // only 2 bytes available
                    0x00, 0x00          // padding to make it 8 bytes
            };
            TtlvObject result = deserializer.deserialize(insufficientData);
            System.out.println("Insufficient value data test passed: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Insufficient value data error: " + e.getMessage());
        }

        // Test negative value length
        try {
            byte[] negativeLengthData = {
                    0x01, 0x02, 0x03,  // tag
                    0x05,               // type
                    (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,  // length (-1)
                    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00   // padding to make it 16 bytes
            };
            deserializer.deserialize(negativeLengthData);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative length error: " + e.getMessage());
        }
    }
}
