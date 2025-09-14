package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SampleStructure JSON Tests")
class SampleStructureJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize SampleStructure")
    void roundTrip() {
        SampleStructure original = KmipTestDataFactory.createSampleStructure();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, SampleStructure.class);
    }

    @Test
    @DisplayName("Round-trip: complex nested structures")
    void roundTrip_complex() {
        List<SampleStructure> structures = KmipTestDataFactory.createSampleStructures(5);
        for (SampleStructure structure : structures) {
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
        }
    }

    @Test
    @DisplayName("Structure: expected JSON fields present for SampleStructure")
    void structure_expectFields() {
        SampleStructure structure = KmipTestDataFactory.createSampleStructure();
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                structure,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("\"SecretData\"");
                });
    }
}
