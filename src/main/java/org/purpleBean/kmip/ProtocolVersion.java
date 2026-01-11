package org.purpleBean.kmip;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class ProtocolVersion implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtocolVersion.class);
        }
    }

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
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return true;
    }

    @Data
    @Builder(toBuilder = true)
    public static class ProtocolVersionMajor implements KmipDataType {
        public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MAJOR);
        public static final EncodingType encodingType = EncodingType.INTEGER;

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtocolVersionMajor.class);
            }
        }

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
        public KmipTag getKmipTag() {
            return kmipTag;
        }

        @Override
        public EncodingType getEncodingType() {
            return encodingType;
        }

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    @Data
    @Builder(toBuilder = true)
    public static class ProtocolVersionMinor implements KmipDataType {
        public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MINOR);
        public static final EncodingType encodingType = EncodingType.INTEGER;

        static {
            for (KmipSpec spec : supportedVersions) {
                if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
                KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtocolVersionMajor.class);
            }
        }

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
        public KmipTag getKmipTag() {
            return kmipTag;
        }

        @Override
        public EncodingType getEncodingType() {
            return encodingType;
        }

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }
}
