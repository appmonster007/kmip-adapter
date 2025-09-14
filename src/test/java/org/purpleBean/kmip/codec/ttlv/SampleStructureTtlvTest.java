package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SampleStructure TTLV Tests")
class SampleStructureTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: SampleStructure TTLV")
    void roundTrip_sampleStructure() {
        SampleStructure original = KmipTestDataFactory.createSampleStructure();
        assertRoundTrip(original);
    }

    @Test
    @DisplayName("Round-trip: complex nested structures")
    void roundTrip_complex() {
        List<SampleStructure> structures = KmipTestDataFactory.createSampleStructures(5);
        for (SampleStructure structure : structures) {
            assertRoundTrip(structure);
        }
    }

    private void assertRoundTrip(SampleStructure original) {
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to TTLV", e);
        }
        SampleStructure deserialized;
        try {
            deserialized = ttlvMapper.readValue(buffer, SampleStructure.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize from TTLV", e);
        }
        assertThat(deserialized).isEqualTo(original);
    }
}
