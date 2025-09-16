package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ActivationDateAttribute TTLV Tests")
class ActivationDateAttributeTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: ActivationDateAttribute TTLV")
    void roundTrip_activationDate() {
        ActivationDateAttribute original = KmipTestDataFactory.createActivationDateAttribute();
        assertRoundTrip(original);
    }

    @Test
    @DisplayName("Round-trip: ActivationDateAttribute various dates")
    void roundTrip_variousDates() {
        List<ActivationDateAttribute> dates = List.of(
                KmipTestDataFactory.createActivationDateAttribute(KmipTestDataFactory.BoundaryData.epochDateTime()),
                KmipTestDataFactory.createActivationDateAttribute(OffsetDateTime.now()),
                KmipTestDataFactory.createRandomActivationDateAttribute()
        );
        for (ActivationDateAttribute d : dates) {
            assertRoundTrip(d);
        }
    }

    @Test
    @DisplayName("UnsupportedVersion context: ActivationDateAttribute TTLV serialization should fail")
    void unsupportedVersion_ttlvSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> ttlvMapper.writeValueAsByteBuffer(KmipTestDataFactory.createActivationDateAttribute()))
                        .isInstanceOf(Exception.class));
    }

    private void assertRoundTrip(ActivationDateAttribute original) {
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to TTLV", e);
        }
        ActivationDateAttribute deserialized;
        try {
            deserialized = ttlvMapper.readValue(buffer, ActivationDateAttribute.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize from TTLV", e);
        }
        // Compare instants at second precision to avoid offset/precision differences
        assertThat(deserialized.getDateTime().toInstant().truncatedTo(ChronoUnit.SECONDS))
                .isEqualTo(original.getDateTime().toInstant().truncatedTo(ChronoUnit.SECONDS));
    }
}
