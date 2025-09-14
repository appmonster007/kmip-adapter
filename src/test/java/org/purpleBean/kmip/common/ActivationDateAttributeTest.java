package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ActivationDateAttribute Tests")
class ActivationDateAttributeTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create ActivationDateAttribute with builder")
        void shouldCreateActivationDateAttributeWithBuilder() {
            // Given
            OffsetDateTime dateTime = OffsetDateTime.now();

            // When
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(dateTime).build();

            // Then
            assertThat(attribute.getDateTime()).isEqualTo(dateTime);
            assertThat(attribute.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.ACTIVATION_DATE);
            assertThat(attribute.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        }

        @Test
        @DisplayName("Should handle various date formats")
        void shouldHandleVariousDateFormats() {
            // Given
            OffsetDateTime[] testDates = {
                    OffsetDateTime.now(),
                    OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC),
                    OffsetDateTime.of(2023, 12, 31, 23, 59, 59, 999_999_999, ZoneOffset.of("+05:30")),
                    KmipTestDataFactory.BoundaryData.epochDateTime()
            };

            // When & Then
            for (OffsetDateTime dateTime : testDates) {
                ActivationDateAttribute attribute =
                        ActivationDateAttribute.builder().dateTime(dateTime).build();

                assertThat(attribute.getDateTime()).isEqualTo(dateTime);
            }
        }

        @Test
        @DisplayName("Should handle null dateTime")
        void shouldHandleNullDateTime() {
            // Given & When & Then
            assertThatThrownBy(() -> ActivationDateAttribute.builder().dateTime(null).build())
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dateTime is marked non-null but is null");
        }

        @Test
        @DisplayName("Should accept past activation dates")
        void shouldAcceptPastActivationDates() {
            // Given
            OffsetDateTime pastDate = OffsetDateTime.now().minusDays(1);

            // When
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(pastDate).build();

            // Then
            assertThat(attribute.getDateTime()).isEqualTo(pastDate);
        }

        @Test
        @DisplayName("Should accept future activation dates")
        void shouldAcceptFutureActivationDates() {
            // Given
            OffsetDateTime futureDate = OffsetDateTime.now().plusDays(1);

            // When
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(futureDate).build();

            // Then
            assertThat(attribute.getDateTime()).isEqualTo(futureDate);
        }
    }

    @Nested
    @DisplayName("KMIP Structure Properties")
    class KmipStructureProperties {

        @Test
        @DisplayName("Should have correct KMIP tag")
        void shouldHaveCorrectKmipTag() {
            // Given
            ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            assertThat(attribute.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.ACTIVATION_DATE);
            assertThat(attribute.getKmipTag().getDescription()).isEqualTo("ActivationDate");
        }

        @Test
        @DisplayName("Should have correct encoding type")
        void shouldHaveCorrectEncodingType() {
            // Given
            ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            assertThat(attribute.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        }

        @Test
        @DisplayName("Should support KMIP specification")
        void shouldSupportKmipSpecification() {
            // Given
            ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            assertThat(attribute.isSupportedFor(defaultSpec)).isTrue();
        }
    }

    @Nested
    @DisplayName("Equality and Hash Code")
    class EqualityAndHashCode {

        @Test
        @DisplayName("Should be equal when dateTime matches")
        void shouldBeEqualWhenDateTimeMatches() {
            // Given
            OffsetDateTime dateTime = OffsetDateTime.now();
            ActivationDateAttribute attr1 = ActivationDateAttribute.builder().dateTime(dateTime).build();
            ActivationDateAttribute attr2 = ActivationDateAttribute.builder().dateTime(dateTime).build();

            // When & Then
            assertThat(attr1).isEqualTo(attr2);
            assertThat(attr1.hashCode()).isEqualTo(attr2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when dateTime differs")
        void shouldNotBeEqualWhenDateTimeDiffers() {
            // Given
            OffsetDateTime dateTime1 = OffsetDateTime.now();
            OffsetDateTime dateTime2 = dateTime1.plusDays(1);
            ActivationDateAttribute attr1 = ActivationDateAttribute.builder().dateTime(dateTime1).build();
            ActivationDateAttribute attr2 = ActivationDateAttribute.builder().dateTime(dateTime2).build();

            // When & Then
            assertThat(attr1).isNotEqualTo(attr2);
        }

        @Test
        @DisplayName("Should handle null dateTime in equality")
        void shouldHandleNullDateTimeInEquality() {
            // Given
            ActivationDateAttribute attribute1 = KmipTestDataFactory.createNullActivationDateAttribute();
            ActivationDateAttribute attribute2 = KmipTestDataFactory.createNullActivationDateAttribute();
            ActivationDateAttribute attribute3 = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            assertThat(attribute1).isEqualTo(attribute2);
            assertThat(attribute1).isNotEqualTo(attribute3);
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize and deserialize JSON correctly")
        void shouldSerializeAndDeserializeJsonCorrectly() {
            // Given
            ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            SerializationTestUtils.performJsonRoundTrip(
                    jsonMapper, original, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize XML correctly")
        void shouldSerializeAndDeserializeXmlCorrectly() {
            // Given
            ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            SerializationTestUtils.performXmlRoundTrip(
                    xmlMapper, original, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("Should handle various date formats in serialization")
        void shouldHandleVariousDateFormatsInSerialization() {
            // Given
            OffsetDateTime[] testDates = {
                    KmipTestDataFactory.BoundaryData.epochDateTime(),
                    OffsetDateTime.now(),
                    OffsetDateTime.of(2024, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC)
            };

            // When & Then
            for (OffsetDateTime dateTime : testDates) {
                ActivationDateAttribute attribute =
                        ActivationDateAttribute.builder().dateTime(dateTime).build();

                SerializationTestUtils.performBothRoundTrips(
                        jsonMapper, xmlMapper, attribute, ActivationDateAttribute.class);
            }
        }

        @Test
        @DisplayName("Should produce expected JSON structure")
        void shouldProduceExpectedJsonStructure() {
            // Given
            ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();

            // When & Then
            SerializationTestUtils.testJsonSerialization(
                    jsonMapper,
                    attribute,
                    json -> {
                        SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                        assertThat(json).contains("2024-01-01T00:00Z");
                    });
        }

        @Test
        @DisplayName("Should handle null dateTime in serialization")
        void shouldHandleNullDateTimeInSerialization() {
            // Given
            ActivationDateAttribute attributeWithNull =
                    KmipTestDataFactory.createNullActivationDateAttribute();

            // When & Then
            SerializationTestUtils.testNullHandling(
                    jsonMapper, attributeWithNull, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("UnsupportedVersion context: ActivationDateAttribute JSON serialization should fail")
        void unsupportedVersion_jsonSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                                    () -> jsonMapper.writeValueAsString(KmipTestDataFactory.createActivationDateAttribute()))
                            .isInstanceOf(Exception.class));
        }

        @Test
        @DisplayName("UnsupportedVersion context: ActivationDateAttribute XML serialization should fail")
        void unsupportedVersion_xmlSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                                    () -> xmlMapper.writeValueAsString(KmipTestDataFactory.createActivationDateAttribute()))
                            .isInstanceOf(Exception.class));
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Conditions")
    class EdgeCasesAndBoundaryConditions {

        @Test
        @DisplayName("Should handle minimum date values")
        void shouldHandleMinimumDateValues() {
            // Given
            OffsetDateTime minDate = KmipTestDataFactory.BoundaryData.minDateTime();

            // When
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(minDate).build();

            // Then
            assertThat(attribute.getDateTime()).isEqualTo(minDate);
        }

        @Test
        @DisplayName("Should handle maximum date values")
        void shouldHandleMaximumDateValues() {
            // Given
            OffsetDateTime maxDate = KmipTestDataFactory.BoundaryData.maxDateTime();

            // When
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(maxDate).build();

            // Then
            assertThat(attribute.getDateTime()).isEqualTo(maxDate);
        }

        @Test
        @DisplayName("Should handle different time zones")
        void shouldHandleDifferentTimeZones() {
            // Given
            OffsetDateTime utcTime = OffsetDateTime.of(2024, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);
            OffsetDateTime istTime = OffsetDateTime.of(2024, 1, 1, 17, 30, 0, 0, ZoneOffset.of("+05:30"));
            OffsetDateTime estTime = OffsetDateTime.of(2024, 1, 1, 7, 0, 0, 0, ZoneOffset.of("-05:00"));

            // When
            ActivationDateAttribute utcAttr = ActivationDateAttribute.builder().dateTime(utcTime).build();
            ActivationDateAttribute istAttr = ActivationDateAttribute.builder().dateTime(istTime).build();
            ActivationDateAttribute estAttr = ActivationDateAttribute.builder().dateTime(estTime).build();

            // Then - All should serialize/deserialize correctly
            SerializationTestUtils.performBothRoundTrips(
                    jsonMapper, xmlMapper, utcAttr, ActivationDateAttribute.class);
            SerializationTestUtils.performBothRoundTrips(
                    jsonMapper, xmlMapper, istAttr, ActivationDateAttribute.class);
            SerializationTestUtils.performBothRoundTrips(
                    jsonMapper, xmlMapper, estAttr, ActivationDateAttribute.class);
        }

        @Test
        @DisplayName("Should maintain immutability")
        void shouldMaintainImmutability() {
            // Given
            OffsetDateTime originalDateTime = OffsetDateTime.now();
            ActivationDateAttribute attribute =
                    ActivationDateAttribute.builder().dateTime(originalDateTime).build();

            // When - Attempt to modify the returned dateTime (should not affect original)
            OffsetDateTime retrievedDateTime = attribute.getDateTime();

            // Then - Original should remain unchanged
            assertThat(attribute.getDateTime()).isEqualTo(originalDateTime);
            assertThat(retrievedDateTime).isEqualTo(originalDateTime);
        }
    }
}
