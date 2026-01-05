package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.math.BigInteger;
import java.util.Set;

/**
 * KMIP PublicExponent dataType.
 */
@Data
@Builder(toBuilder = true)
public class PublicExponent implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PUBLIC_EXPONENT);
    public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PublicExponent.class);
        }
    }


    @NonNull
    private final BigInteger value;

    public static PublicExponent of(@NonNull BigInteger value) {
        return PublicExponent.builder().value(value).build();
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