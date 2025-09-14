package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SampleStructure XML Tests")
class SampleStructureXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize SampleStructure")
    void roundTrip() {
        SampleStructure original = KmipTestDataFactory.createSampleStructure();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, SampleStructure.class);
    }

    @Test
    @DisplayName("Round-trip: complex nested structures")
    void roundTrip_complex() {
        List<SampleStructure> structures = KmipTestDataFactory.createSampleStructures(5);
        for (SampleStructure structure : structures) {
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, structure, SampleStructure.class);
        }
    }

    @Test
    @DisplayName("Structure: expected XML fields present for SampleStructure")
    void structure_expectFields() {
        SampleStructure structure = KmipTestDataFactory.createSampleStructure();
        SerializationTestUtils.testXmlSerialization(
                xmlMapper,
                structure,
                xml -> {
                    assertThat(xml).contains("<SecretData>");
                    assertThat(xml).contains("<ActivationDate");
                    assertThat(xml).contains("<State");
                }
        );
    }
}
