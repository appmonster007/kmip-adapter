package org.purpleBean.ttlv;

import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvModule;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TtlvRoundTripTest extends BaseKmipTest {

    private TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    void protocolVersion_roundTrip() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            ProtocolVersion original = ProtocolVersion.of(1, 2);

            // Serialize to TTLV
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }

            // Deserialize back to object
            ProtocolVersion deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, ProtocolVersion.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }

            // Verify round-trip
            assertThat(deserialized).isEqualTo(original);

            // Verify TTLV structure - the buffer should contain valid TTLV data
            buffer.rewind();
            assertThat(buffer.remaining()).isGreaterThan(8); // At least tag (3) + type (1) + length (4)

            // Basic TTLV structure verification
            byte[] ttlvBytes = new byte[buffer.remaining()];
            buffer.get(ttlvBytes);

            // Verify the first 3 bytes are the tag (0x42, 0x00, 0x69 for ProtocolVersion)
            assertThat(ttlvBytes[0]).isEqualTo((byte) 0x42);
            assertThat(ttlvBytes[1]).isEqualTo((byte) 0x00);
            assertThat(ttlvBytes[2]).isEqualTo((byte) 0x69);

            // Verify type is Structure (0x01)
            assertThat(ttlvBytes[3]).isEqualTo((byte) 0x01);

            // Verify length is 32 bytes (0x20) for major + minor version
            int length = (ttlvBytes[4] << 24) |
                    ((ttlvBytes[5] & 0xFF) << 16) |
                    ((ttlvBytes[6] & 0xFF) << 8) |
                    (ttlvBytes[7] & 0xFF);
            assertThat(length).isEqualTo(32); // 16 bytes per integer field (8 bytes data + 8 bytes padding)

            // Verify the structure contains two integer fields
            // First field (ProtocolVersionMajor)
            assertThat(ttlvBytes[8]).isEqualTo((byte) 0x42);
            assertThat(ttlvBytes[9]).isEqualTo((byte) 0x00);
            assertThat(ttlvBytes[10]).isEqualTo((byte) 0x6a); // Major version tag
            assertThat(ttlvBytes[11]).isEqualTo((byte) 0x02); // Integer type

            // Second field (ProtocolVersionMinor)
            assertThat(ttlvBytes[24]).isEqualTo((byte) 0x42);
            assertThat(ttlvBytes[25]).isEqualTo((byte) 0x00);
            assertThat(ttlvBytes[26]).isEqualTo((byte) 0x6b); // Minor version tag
            assertThat(ttlvBytes[27]).isEqualTo((byte) 0x02); // Integer type
        });
    }

    @Test
    void state_roundTrip_withCustom() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            State.register(-1341234, "Alive", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State original = new State(State.fromName(KmipSpec.V1_2, "Alive"));

            // Serialize to TTLV
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }

            // Deserialize back to object
            State deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, State.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }

            // Verify round-trip
            assertThat(deserialized).isEqualTo(original);
        });
    }

    @Test
    void activationDate_roundTrip() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
            ActivationDateAttribute original = ActivationDateAttribute.builder()
                    .dateTime(now)
                    .build();

            // Serialize to TTLV
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }

            // Deserialize back to object
            ActivationDateAttribute deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, ActivationDateAttribute.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }

            // Verify round-trip
            assertThat(deserialized).isEqualTo(original);
        });
    }

    @Test
    void sampleStructure_roundTrip() {
        withKmipSpec(KmipSpec.V1_2, () -> {
            SampleStructure original = SampleStructure.builder()
                    .activationDate(ActivationDateAttribute.builder()
                            .dateTime(OffsetDateTime.now(ZoneOffset.UTC))
                            .build())
                    .state(new State(State.Standard.ACTIVE))
                    .build();

            // Serialize to TTLV
            ByteBuffer buffer;
            try {
                buffer = ttlvMapper.writeValueAsByteBuffer(original);
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize to TTLV", e);
            }

            // Deserialize back to object
            SampleStructure deserialized;
            try {
                deserialized = ttlvMapper.readValue(buffer, SampleStructure.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to deserialize from TTLV", e);
            }

            // Verify round-trip
            assertThat(deserialized).isEqualTo(original);
        });
    }
}
