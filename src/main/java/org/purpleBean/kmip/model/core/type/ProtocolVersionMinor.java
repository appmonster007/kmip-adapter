package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class ProtocolVersionMinor implements KmipDataType {
    public static final KmipTag kmipTag = KmipTag.Standard.PROTOCOL_VERSION_MINOR.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.values());

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtocolVersionMinor.class);
        }
    }

    @EqualsAndHashCode.Include
    @NonNull
    private final Integer value;

    @Builder
    private ProtocolVersionMinor(int value) {
        this.value = value;
        validate();
    }

    public static ProtocolVersionMinor of(int minor) {
        return ProtocolVersionMinor.builder()
                .value(minor)
                .build();
    }

    private void validate() {
        // No validation needed for this structure
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
        return true;
    }
}
