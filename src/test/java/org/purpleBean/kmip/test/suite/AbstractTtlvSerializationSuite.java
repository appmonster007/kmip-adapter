package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Reusable TTLV serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract TTLV Serialization Suite")
public abstract class AbstractTtlvSerializationSuite<T> extends BaseKmipTest {

    protected abstract Class<T> type();

    protected abstract T createDefault();

    protected T createVariant() {
        return createDefault();
    }

    protected TtlvMapper mapper() {
        return getTtlvMapper();
    }

    /**
     * Override when the type is allowed to serialize even under KmipSpec.UnsupportedVersion.
     * Default is true (negative test expects failure).
     */
    protected boolean unsupportedSpecShouldFailSerialize() {
        return true;
    }

    @Test
    @DisplayName("TTLV: round-trip default instance")
    void ttlv_roundTrip_default() {
        T original = createDefault();
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
            System.out.println("TTLV: \n" + TtlvObject.fromBuffer(buffer.duplicate()).getStructuredByteString());
            T restored = ttlvMapper.readValue(buffer, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
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
            buffer = mapper().writeValueAsByteBuffer(original);
            System.out.println("TTLV: \n" + TtlvObject.fromBuffer(buffer.duplicate()).getStructuredByteString());
            T restored = mapper().readValue(buffer, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
        } catch (IOException e) {
            throw new AssertionError("TTLV round-trip failed", e);
        }
    }

    @Test
    @DisplayName("TTLV: unsupported KMIP spec should fail serialize")
    void ttlv_unsupportedSpec_failsSerialize() {
        if (unsupportedSpecShouldFailSerialize()) {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(() -> mapper().writeValueAsByteBuffer(createDefault()))
                            .isInstanceOf(Exception.class));
        }
    }

    /**
     * Hook for subclasses to relax equality comparison on round-trips.
     * Default uses Objects.equals (delegates to equals()).
     */
    protected boolean equalsRelaxed(T a, T b) {
        return Objects.equals(a, b);
    }
}
