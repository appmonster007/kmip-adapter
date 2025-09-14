package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ActivationDateAttribute JSON Tests")
class ActivationDateAttributeJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize ActivationDateAttribute")
    void roundTrip() {
        ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, ActivationDateAttribute.class);
    }

    @Test
    @DisplayName("Round-trip: various date inputs")
    void roundTrip_variousDates() {
        List<ActivationDateAttribute> dates = List.of(
                KmipTestDataFactory.createActivationDateAttribute(KmipTestDataFactory.BoundaryData.epochDateTime()),
                KmipTestDataFactory.createActivationDateAttribute(OffsetDateTime.now()),
                KmipTestDataFactory.createRandomActivationDateAttribute()
        );

        for (ActivationDateAttribute date : dates) {
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, date, ActivationDateAttribute.class);
        }
    }

    @Test
    @DisplayName("Structure: expected JSON fields present for ActivationDateAttribute")
    void structure_expectFields() {
        ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                attribute,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("\"ActivationDate\"");
                }
        );
    }
}
