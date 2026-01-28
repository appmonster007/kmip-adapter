package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class ProtocolVersion implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.PROTOCOL_VERSION.inst();
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.values());

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

    @Builder
    private ProtocolVersion(@NonNull ProtocolVersionMajor protocolVersionMajor, @NonNull ProtocolVersionMinor protocolVersionMinor) {
        this.protocolVersionMajor = protocolVersionMajor;
        this.protocolVersionMinor = protocolVersionMinor;
        validate();
    }

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

    private void validate() {
        // No validation needed for this structure
    }

    public List<KmipDataType> getValue() {
        return List.of(protocolVersionMajor, protocolVersionMinor);
    }

    public int getMajor() {
        return protocolVersionMajor.getValue();
    }

    public int getMinor() {
        return protocolVersionMinor.getValue();
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
        return true;
    }
}