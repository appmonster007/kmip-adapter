package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.purpleBean.kmip.*;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class ProtocolVersionMajor implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTOCOL_VERSION_MAJOR);
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.values());

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
        return true;
    }
}
