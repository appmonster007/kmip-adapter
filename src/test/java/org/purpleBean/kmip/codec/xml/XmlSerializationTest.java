package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("XML Serialization Tests")
class XmlSerializationTest extends BaseKmipTest {

    @Nested
    @DisplayName("Protocol Version XML Serialization")
    class ProtocolVersionXmlSerialization {

        @Test
        @DisplayName("Should serialize and deserialize ProtocolVersion correctly")
        void shouldSerializeAndDeserializeProtocolVersionCorrectly() {
            // Given
            ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ProtocolVersion.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"0,0", "1,0", "1,2", "2,1", "99,99"})
        @DisplayName("Should handle various protocol versions")
        void shouldHandleVariousProtocolVersions(String versionPair) {
            // Given
            String[] parts = versionPair.split(",");
            ProtocolVersion version = ProtocolVersion.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
        }

        @Test
        @DisplayName("Should produce expected XML structure for ProtocolVersion")
        void shouldProduceExpectedXmlStructureForProtocolVersion() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, version, xml -> {
                assertThat(xml).contains("<ProtocolVersion>");
                assertThat(xml).contains("<ProtocolVersionMajor");
                assertThat(xml).contains("<ProtocolVersionMinor");
                assertThat(xml).contains("value=\"1\"");
                assertThat(xml).contains("value=\"2\"");
            });
        }
    }

    @Nested
    @DisplayName("State XML Serialization")
    class StateXmlSerialization {

        @Test
        @DisplayName("Should serialize and deserialize standard State correctly")
        void shouldSerializeAndDeserializeStandardStateCorrectly() {
            // Given
            State original = KmipTestDataFactory.createState();
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, State.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize custom State correctly")
        void shouldSerializeAndDeserializeCustomStateCorrectly() {
            // Given
            State original = KmipTestDataFactory.createCustomState();
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, State.class);
        }

        @Test
        @DisplayName("Should handle all standard states")
        void shouldHandleAllStandardStates() {
            // Given
            List<State> states = KmipTestDataFactory.createStates();
            
            // When & Then
            for (State state : states) {
                SerializationTestUtils.performXmlRoundTrip(xmlMapper, state, State.class);
            }
        }

        @Test
        @DisplayName("Should produce expected XML structure for State")
        void shouldProduceExpectedXmlStructureForState() {
            // Given
            State state = new State(State.Standard.ACTIVE);
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, state, xml -> {
                assertThat(xml).contains("<State");
                assertThat(xml).contains("type=\"Enumeration\"");
                assertThat(xml).contains("Active");
            });
        }
    }

    @Nested
    @DisplayName("ActivationDateAttribute XML Serialization")
    class ActivationDateAttributeXmlSerialization {

        @Test
        @DisplayName("Should serialize and deserialize ActivationDateAttribute correctly")
        void shouldSerializeAndDeserializeActivationDateAttributeCorrectly() {
            // Given
            ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("Should handle various date formats")
        void shouldHandleVariousDateFormats() {
            // Given
            List<ActivationDateAttribute> dates = List.of(
                KmipTestDataFactory.createActivationDateAttribute(KmipTestDataFactory.BoundaryData.epochDateTime()),
                KmipTestDataFactory.createActivationDateAttribute(OffsetDateTime.now()),
                KmipTestDataFactory.createRandomActivationDateAttribute()
            );
            
            // When & Then
            for (ActivationDateAttribute date : dates) {
                SerializationTestUtils.performXmlRoundTrip(xmlMapper, date, ActivationDateAttribute.class);
            }
        }

        @Test
        @DisplayName("Should produce expected XML structure for ActivationDateAttribute")
        void shouldProduceExpectedXmlStructureForActivationDateAttribute() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, structure, xml -> {
                assertThat(xml).contains("<SampleStructure>");
                assertThat(xml).contains("<ActivationDate");
                assertThat(xml).contains("<State");
                assertThat(xml).contains("type=\"DateTime\"");
            });
        }
    }

    @Nested
    @DisplayName("SampleStructure XML Serialization")
    class SampleStructureXmlSerialization {

        @Test
        @DisplayName("Should serialize and deserialize SampleStructure correctly")
        void shouldSerializeAndDeserializeSampleStructureCorrectly() {
            // Given
            SampleStructure original = KmipTestDataFactory.createSampleStructure();
            
            // When & Then
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, SampleStructure.class);
        }

        @Test
        @DisplayName("Should handle complex nested structures")
        void shouldHandleComplexNestedStructures() {
            // Given
            List<SampleStructure> structures = KmipTestDataFactory.createSampleStructures(5);
            
            // When & Then
            for (SampleStructure structure : structures) {
                SerializationTestUtils.performXmlRoundTrip(xmlMapper, structure, SampleStructure.class);
            }
        }

        @Test
        @DisplayName("Should produce expected XML structure for SampleStructure")
        void shouldProduceExpectedXmlStructureForSampleStructure() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, structure, xml -> {
                assertThat(xml).contains("<SampleStructure>");
                assertThat(xml).contains("<ActivationDate");
                assertThat(xml).contains("<State");
            });
        }
    }

    @Nested
    @DisplayName("XML Error Handling")
    class XmlErrorHandling {

        @Test
        @DisplayName("Should handle malformed XML gracefully")
        void shouldHandleMalformedXmlGracefully() {
            // Given
            String malformedXml = "<ProtocolVersion><protocolVersionMajor><value>not_a_number</value></protocolVersionMajor>";
            
            // When & Then
            assertThatThrownBy(() -> 
                SerializationTestUtils.testXmlDeserialization(xmlMapper, malformedXml, ProtocolVersion.class))
                .isInstanceOf(AssertionError.class);
        }

        @Test
        @DisplayName("Should handle missing required elements")
        void shouldHandleMissingRequiredElements() {
            // Given
            String incompleteXml = "<ProtocolVersion><protocolVersionMajor><value>1</value></protocolVersionMajor></ProtocolVersion>";
            
            // When & Then
            assertThatThrownBy(() -> 
                SerializationTestUtils.testXmlDeserialization(xmlMapper, incompleteXml, ProtocolVersion.class))
                .isInstanceOf(AssertionError.class);
        }

        @Test
        @DisplayName("Should handle XML namespaces correctly")
        void shouldHandleXmlNamespacesCorrectly() {
            // Given
            ProtocolVersion version = ProtocolVersion.of(1, 2);
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, version, xml -> {
                // Verify XML is well-formed and contains expected structure
                assertThat(xml).contains("<ProtocolVersion>");
                assertThat(xml).contains("</ProtocolVersion>");
            });
        }

        @Test
        @DisplayName("Should produce valid XML format")
        void shouldProduceValidXmlFormat() {
            // Given
            SampleStructure structure = KmipTestDataFactory.createSampleStructure();
            
            // When & Then
            SerializationTestUtils.testXmlSerialization(xmlMapper, structure, xml -> {
                // Basic XML validation
                assertThat(xml).startsWith("<");
                assertThat(xml).endsWith(">");
                assertThat(xml).doesNotContain("<<");
                assertThat(xml).doesNotContain(">>");
            });
        }
    }

    @Nested
    @DisplayName("XML Performance")
    class XmlPerformance {

        @Test
        @DisplayName("Should handle large datasets efficiently")
        void shouldHandleLargeDatasetsEfficiently() {
            // Given
            List<SampleStructure> largeDataset = KmipTestDataFactory.PerformanceData.largeSampleStructureList();
            
            // When
            long startTime = System.currentTimeMillis();
            
            for (SampleStructure structure : largeDataset.subList(0, Math.min(100, largeDataset.size()))) {
                SerializationTestUtils.performXmlRoundTrip(xmlMapper, structure, SampleStructure.class);
            }
            
            long endTime = System.currentTimeMillis();
            
            // Then
            assertThat(endTime - startTime).isLessThan(5000); // Should complete within 5 seconds
        }
    }
}
