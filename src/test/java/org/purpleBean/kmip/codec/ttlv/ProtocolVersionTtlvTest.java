package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProtocolVersion TTLV Tests")
class ProtocolVersionTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: ProtocolVersion TTLV")
    void roundTrip_protocolVersion() {
        withKmipSpec(
                KmipSpec.V1_2,
                () -> {
                    ProtocolVersion original = ProtocolVersion.of(1, 2);

                    ByteBuffer buffer;
                    try {
                        buffer = ttlvMapper.writeValueAsByteBuffer(original);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to serialize to TTLV", e);
                    }

                    ProtocolVersion deserialized;
                    try {
                        deserialized = ttlvMapper.readValue(buffer, ProtocolVersion.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to deserialize from TTLV", e);
                    }

                    assertThat(deserialized).isEqualTo(original);
                });
    }
}
