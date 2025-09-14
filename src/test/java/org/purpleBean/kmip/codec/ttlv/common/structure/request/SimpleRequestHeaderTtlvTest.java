package org.purpleBean.kmip.codec.ttlv.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SimpleRequestHeader TTLV Codec Tests")
class SimpleRequestHeaderTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    private SimpleRequestHeader header() {
        return SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(1, 2)).build();
    }

    @Test
    @DisplayName("Round-trip TTLV: SimpleRequestHeader")
    void roundTrip_ttlv() {
        SimpleRequestHeader original = header();
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to TTLV", e);
        }
        SimpleRequestHeader deserialized;
        try {
            deserialized = ttlvMapper.readValue(buffer, SimpleRequestHeader.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize from TTLV", e);
        }
        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("UnsupportedVersion: TTLV round-trip should succeed (header supports all specs)")
    void unsupportedVersion_ttlv_deserializeFails() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            SimpleRequestHeader original = header();
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            org.assertj.core.api.Assertions.assertThatThrownBy(
                    () -> ttlvMapper.readValue(buffer, SimpleRequestHeader.class))
                    .isInstanceOf(Exception.class);
        });
    }
}
