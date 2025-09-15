package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("State TTLV Tests")
class StateTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: standard State TTLV")
    void roundTrip_standard() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            State original = new State(State.Standard.ACTIVE);
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }
            State deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, State.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }
            assertThat(deserialized).isEqualTo(original);
        });
    }

    @Test
    @DisplayName("Round-trip: custom State TTLV")
    void roundTrip_custom() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            State.register(-1341234, "Alive", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State original = new State(State.fromName(KmipSpec.V1_2, "Alive"));
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }
            State deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, State.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }
            assertThat(deserialized).isEqualTo(original);
        });
    }

    @Test
    @DisplayName("UnsupportedVersion context: State TTLV serialization should fail")
    void unsupportedVersion_ttlvSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> {
                                    State original = new State(State.Standard.ACTIVE);
                                    ttlvMapper.writeValueAsByteBuffer(original);
                                })
                        .isInstanceOf(Exception.class));
    }
}
