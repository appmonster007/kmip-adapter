package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ActivationDateAttribute XML Tests")
class ActivationDateAttributeXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize ActivationDateAttribute")
    void roundTrip() {
        ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ActivationDateAttribute.class);
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
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, date, ActivationDateAttribute.class);
        }
    }

    @Test
    @DisplayName("Structure: expected XML fields present for ActivationDateAttribute")
    void structure_expectFields() {
        ActivationDateAttribute attribute = KmipTestDataFactory.createActivationDateAttribute();
        SerializationTestUtils.testXmlSerialization(
                xmlMapper,
                attribute,
                xml -> {
                    assertThat(xml).contains("<ActivationDate");
                    assertThat(xml).contains("type=\"DateTime\"");
                }
        );
    }
}
