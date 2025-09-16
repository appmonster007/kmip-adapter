package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Reusable TTLV serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract TTLV Serialization Suite")
public abstract class AbstractTtlvSerializationSuite<T> extends BaseKmipTest {

    protected abstract Class<T> type();

    protected abstract T createDefault();

    protected T createVariant() { return createDefault(); }

    protected TtlvMapper ttlvMapper;

    @Override
    protected void setupTestSpecificResources() {
        ttlvMapper = new TtlvMapper();
        ttlvMapper.registerModule(new KmipTtlvModule());
    }

    @Test
    @DisplayName("TTLV: round-trip default instance")
    void ttlv_roundTrip_default() {
        T original = createDefault();
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
            T restored = ttlvMapper.readValue(buffer, type());
            assertThat(restored).isEqualTo(original);
        } catch (IOException e) {
            throw new AssertionError("TTLV round-trip failed", e);
        }
    }

    @Test
    @DisplayName("TTLV: round-trip variant instance")
    void ttlv_roundTrip_variant() {
        T original = createVariant();
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
            T restored = ttlvMapper.readValue(buffer, type());
            assertThat(restored).isEqualTo(original);
        } catch (IOException e) {
            throw new AssertionError("TTLV round-trip failed", e);
        }
    }

    @Test
    @DisplayName("TTLV: unsupported KMIP spec should fail serialize")
    void ttlv_unsupportedSpec_failsSerialize() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> ttlvMapper.writeValueAsByteBuffer(createDefault()))
                        .isInstanceOf(Exception.class));
    }
}
