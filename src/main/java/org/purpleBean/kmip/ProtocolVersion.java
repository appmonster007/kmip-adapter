package org.purpleBean.kmip;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class ProtocolVersion implements KmipStructure {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION);
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    @NonNull
    private final ProtocolVersionMajor protocolVersionMajor;
    @NonNull
    private final ProtocolVersionMinor protocolVersionMinor;

    // Static factory methods for validation
    public static ProtocolVersion of(int major, int minor) {
        return ProtocolVersion.builder()
                .protocolVersionMajor(ProtocolVersionMajor.of(major))
                .protocolVersionMinor(ProtocolVersionMinor.of(minor))
                .build();
    }

    public static ProtocolVersion of(@NonNull ProtocolVersionMajor protocolVersionMajor, @NonNull ProtocolVersionMinor protocolVersionMinor) {
        Objects.requireNonNull(protocolVersionMajor, "protocolVersionMajor cannot be null");
        Objects.requireNonNull(protocolVersionMinor, "protocolVersionMinor cannot be null");
        return ProtocolVersion.builder()
                .protocolVersionMajor(protocolVersionMajor)
                .protocolVersionMinor(protocolVersionMinor)
                .build();
    }

    public List<KmipDataType> getValues() {
        return List.of(protocolVersionMajor, protocolVersionMinor);
    }

    public int getMajor() {
        return protocolVersionMajor.value;
    }

    public int getMinor() {
        return protocolVersionMinor.value;
    }

    @Override
    public String toString() {
        return String.format("KMIP-ProtocolVersion-V%s.%s", protocolVersionMajor, protocolVersionMinor);
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return true;
    }

    @Data
    @Builder
    public static class ProtocolVersionMajor implements KmipDataType {
        private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MAJOR);
        private final EncodingType encodingType = EncodingType.INTEGER;

        @EqualsAndHashCode.Include
        private final int value;

        public static ProtocolVersionMajor of(int major) {
            return ProtocolVersionMajor.builder()
                    .value(major)
                    .build();
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return true;
        }
    }

    @Data
    @Builder
    public static class ProtocolVersionMinor implements KmipDataType {
        private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MINOR);
        private final EncodingType encodingType = EncodingType.INTEGER;

        @EqualsAndHashCode.Include
        private final int value;

        public static ProtocolVersionMinor of(int minor) {
            return ProtocolVersionMinor.builder()
                    .value(minor)
                    .build();
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @Override
        public boolean isSupportedFor(@NonNull KmipSpec spec) {
            return true;
        }
    }
}
