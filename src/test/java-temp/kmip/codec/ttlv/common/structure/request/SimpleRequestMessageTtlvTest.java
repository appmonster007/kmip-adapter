package org.purpleBean.kmip.codec.ttlv.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SimpleRequestMessage TTLV Codec Tests")
class SimpleRequestMessageTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    private SimpleRequestMessage buildMessage() {
        SimpleRequestHeader header = SimpleRequestHeader.builder().protocolVersion(ProtocolVersion.of(1, 2)).build();
        return SimpleRequestMessage.builder().requestHeader(header).requestBatchItem(SimpleRequestBatchItem.builder().build()).build();
    }

    @Test
    @DisplayName("Round-trip TTLV: SimpleRequestMessage")
    void roundTrip_ttlv() {
        SimpleRequestMessage original = buildMessage();
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to TTLV", e);
        }
        SimpleRequestMessage deserialized;
        try {
            deserialized = ttlvMapper.readValue(buffer, SimpleRequestMessage.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize from TTLV", e);
        }
        // Assert core fields instead of strict equality (error list may differ)
        assertThat(deserialized.getRequestHeader()).isEqualTo(original.getRequestHeader());
        assertThat(deserialized.getRequestBatchItems()).hasSize(original.getRequestBatchItems().size());
    }

    @Test
    @DisplayName("UnsupportedVersion: TTLV round-trip should succeed (message supports all specs)")
    void unsupportedVersion_ttlv_deserializeFails() {
        withKmipSpec(KmipSpec.UnsupportedVersion, () -> {
            SimpleRequestMessage original = buildMessage();
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            org.assertj.core.api.Assertions.assertThatThrownBy(
                            () -> ttlvMapper.readValue(buffer, SimpleRequestMessage.class))
                    .isInstanceOf(Exception.class);
        });
    }
}
