package org.purpleBean.kmip.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
// Removed Spring Boot test annotations - this is now a regular integration test
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

// Regular integration test without Spring Boot - focuses on KMIP adapter functionality
@DisplayName("KMIP Integration Tests")
class KmipIntegrationTest extends BaseKmipTest {

    @Nested
    @DisplayName("End-to-End Serialization Integration")
    class EndToEndSerializationIntegration {

        @Test
        @DisplayName("Should handle complete KMIP message lifecycle")
        void shouldHandleCompleteKmipMessageLifecycle() {
            // Given - Create a complete KMIP structure hierarchy
            ProtocolVersion version = KmipTestDataFactory.createProtocolVersion();
            ActivationDateAttribute activationDate = KmipTestDataFactory.createActivationDateAttribute();
            State state = KmipTestDataFactory.createState();
            SampleStructure structure = SampleStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();

            // When & Then - Test complete round-trip through all formats
            withKmipSpec(KmipSpec.V1_2, () -> {
                // JSON round-trip
                ProtocolVersion jsonVersion = SerializationTestUtils.performJsonRoundTrip(
                    jsonMapper, version, ProtocolVersion.class);
                SampleStructure jsonStructure = SerializationTestUtils.performJsonRoundTrip(
                    jsonMapper, structure, SampleStructure.class);

                // XML round-trip
                ProtocolVersion xmlVersion = SerializationTestUtils.performXmlRoundTrip(
                    xmlMapper, version, ProtocolVersion.class);
                SampleStructure xmlStructure = SerializationTestUtils.performXmlRoundTrip(
                    xmlMapper, structure, SampleStructure.class);

                // Verify cross-format consistency
                assertThat(jsonVersion).isEqualTo(xmlVersion);
                assertThat(jsonStructure).isEqualTo(xmlStructure);
            });
        }

        @Test
        @DisplayName("Should handle complex nested structure serialization")
        void shouldHandleComplexNestedStructureSerialization() {
            // Given - Create complex nested structures
            var structures = KmipTestDataFactory.createSampleStructures(10);
            
            // When & Then - Test batch processing
            for (SampleStructure structure : structures) {
                SerializationTestUtils.performBothRoundTrips(jsonMapper, xmlMapper, structure, SampleStructure.class);
            }
        }
    }

    @Nested
    @DisplayName("Spring Integration")
    class SpringIntegration {

        @Test
        @DisplayName("Should integrate with Spring context properly")
        void shouldIntegrateWithSpringContextProperly() {
            // Given - Spring Boot context should be loaded
            // When & Then - Basic Spring functionality should work
            assertThat(jsonMapper).isNotNull();
            assertThat(xmlMapper).isNotNull();
            
            // Test that Spring doesn't interfere with KMIP operations
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
        }

        @Test
        @DisplayName("Should handle Spring Boot auto-configuration")
        void shouldHandleSpringBootAutoConfiguration() {
            // Given - Auto-configured components should work
            // When
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            
            // Then
            assertThat(KmipCodecContext.getSpec()).isEqualTo(KmipSpec.V1_2);
            
            // Cleanup
            KmipCodecContext.clear();
        }
    }

    @Nested
    @DisplayName("Library Integration")
    class LibraryIntegration {

        @Test
        @DisplayName("Should integrate KMIP components properly")
        void shouldIntegrateKmipComponentsProperly() {
            // Given - KMIP components should work together seamlessly
            // When & Then - Context should provide necessary functionality
            assertThat(jsonMapper).isNotNull();
            assertThat(xmlMapper).isNotNull();
            
            // Should be able to perform serialization with configured mappers
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();
            SerializationTestUtils.performBothRoundTrips(jsonMapper, xmlMapper, structure, SampleStructure.class);
        }

        @Test
        @DisplayName("Should handle KMIP adapter configuration")
        void shouldHandleKmipAdapterConfiguration() {
            // Given - KMIP adapter components should be properly configured
            // When & Then - Should work with default KMIP configuration
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            
            assertThatCode(() -> {
                String json = jsonMapper.writeValueAsString(version);
                ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);
                assertThat(deserialized).isEqualTo(version);
            }).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("Jackson Integration")
    class JacksonIntegration {

        @Test
        @DisplayName("Should integrate with Jackson modules correctly")
        void shouldIntegrateWithJacksonModulesCorrectly() {
            // Given - Jackson modules should be properly registered
            // When & Then - Time handling should work correctly
            ActivationDateAttribute dateAttr = KmipTestDataFactory.createActivationDateAttribute();
            
            SerializationTestUtils.testJsonSerialization(jsonMapper, dateAttr, json -> {
                SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
            });
        }

        @Test
        @DisplayName("Should handle custom serializers and deserializers")
        void shouldHandleCustomSerializersAndDeserializers() {
            // Given - Custom KMIP serializers should be registered
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            
            // When & Then - Custom serialization logic should work
            SerializationTestUtils.testJsonSerialization(jsonMapper, version, json -> {
                // Should use KMIP serialization format with tag/type/value structure
                SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
            });
        }
    }

    @Nested
    @DisplayName("Performance Integration")
    class PerformanceIntegration {

        @Test
        @DisplayName("Should handle large-scale operations efficiently")
        void shouldHandleLargeScaleOperationsEfficiently() {
            // Given
            int largeDatasetSize = 1000;
            long startTime = System.currentTimeMillis();
            
            // When - Process large dataset
            for (int i = 0; i < largeDatasetSize; i++) {
                ProtocolVersion version = ProtocolVersion.of(i % 5, i % 3);
                SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                
                if (i % 2 == 0) {
                    SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
                }
            }
            
            long endTime = System.currentTimeMillis();
            
            // Then - Should complete within reasonable time
            assertThat(endTime - startTime).isLessThan(30000); // 30 seconds max
        }

        @Test
        @DisplayName("Should maintain memory efficiency")
        void shouldMaintainMemoryEfficiency() {
            // Given
            Runtime runtime = Runtime.getRuntime();
            long initialMemory = runtime.totalMemory() - runtime.freeMemory();
            
            // When - Create and process many objects
            for (int i = 0; i < 10000; i++) {
                SampleStructure structure = KmipTestDataFactory.createSampleStructure();
                SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
                
                // Periodic garbage collection hint
                if (i % 1000 == 0) {
                    System.gc();
                }
            }
            
            System.gc(); // Final cleanup
            long finalMemory = runtime.totalMemory() - runtime.freeMemory();
            
            // Then - Memory usage should not grow excessively
            long memoryIncrease = finalMemory - initialMemory;
            assertThat(memoryIncrease).isLessThan(100 * 1024 * 1024); // Less than 100MB increase
        }
    }

    @Nested
    @DisplayName("Error Recovery Integration")
    class ErrorRecoveryIntegration {

        @Test
        @DisplayName("Should recover from serialization errors gracefully")
        void shouldRecoverFromSerializationErrorsGracefully() {
            // Given - Mix of valid and invalid operations
            ProtocolVersion validVersion = ProtocolVersion.of(1, 2);
            
            // When & Then - Should handle errors without breaking subsequent operations
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, validVersion, ProtocolVersion.class);
            
            // Attempt invalid operation
            assertThatThrownBy(() -> {
                String invalidJson = "{\"invalid\": \"structure\"}";
                SerializationTestUtils.testJsonDeserialization(jsonMapper, invalidJson, ProtocolVersion.class);
            }).isInstanceOf(AssertionError.class);
            
            // Should still work after error
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, validVersion, ProtocolVersion.class);
        }

        @Test
        @DisplayName("Should handle codec context corruption recovery")
        void shouldHandleCodecContextCorruptionRecovery() {
            // Given - Normal operation
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            
            // When - Simulate context issues and recovery
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
            
            // Corrupt context
            KmipCodecContext.setSpec(null);
            
            // Recover context
            KmipCodecContext.clear();
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            
            // Then - Should work normally after recovery
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
        }
    }

    @Nested
    @DisplayName("Cross-Version Compatibility")
    class CrossVersionCompatibility {

        @Test
        @DisplayName("Should handle different KMIP versions consistently")
        void shouldHandleDifferentKmipVersionsConsistently() {
            // Given
            ProtocolVersion v12 = ProtocolVersion.of(1, 2);
            
            // When & Then - Test with different spec contexts
            withKmipSpec(KmipSpec.V1_2, () -> {
                SerializationTestUtils.performBothRoundTrips(jsonMapper, xmlMapper, v12, ProtocolVersion.class);
            });
            
            withKmipSpec(KmipSpec.UnknownVersion, () -> {
                SerializationTestUtils.performBothRoundTrips(jsonMapper, xmlMapper, v12, ProtocolVersion.class);
            });
        }

        @Test
        @DisplayName("Should maintain backward compatibility")
        void shouldMaintainBackwardCompatibility() {
            // Given - Create objects with older spec
            withKmipSpec(KmipSpec.UnknownVersion, () -> {
                ProtocolVersion version = ProtocolVersion.of(1, 0);
                
                // When - Serialize with older spec
                String json = assertDoesNotThrow(() -> jsonMapper.writeValueAsString(version));
                
                // Then - Should deserialize with newer spec
                withKmipSpec(KmipSpec.V1_2, () -> {
                    ProtocolVersion deserialized = assertDoesNotThrow(() -> 
                        jsonMapper.readValue(json, ProtocolVersion.class));
                    assertThat(deserialized).isEqualTo(version);
                });
            });
        }
    }
}
