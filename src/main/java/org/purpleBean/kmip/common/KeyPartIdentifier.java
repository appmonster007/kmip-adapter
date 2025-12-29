package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

/**
 * KMIP KeyPartIdentifier dataType.
 */
@Data
@Builder
public class KeyPartIdentifier implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_PART_IDENTIFIER);
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyPartIdentifier.class);
        }
    }


    @NonNull
    private final Integer value;

    public static KeyPartIdentifier of(@NonNull Integer value) {
        return KeyPartIdentifier.builder().value(value).build();
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