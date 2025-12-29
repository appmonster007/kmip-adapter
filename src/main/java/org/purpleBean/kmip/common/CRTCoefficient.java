package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.math.BigInteger;
import java.util.Set;

/**
 * KMIP CRTCoefficient dataType.
 */
@Data
@Builder
public class CRTCoefficient implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CRT_COEFFICIENT);
    public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CRTCoefficient.class);
        }
    }


    @NonNull
    private final BigInteger value;

    public static CRTCoefficient of(@NonNull BigInteger value) {
        return CRTCoefficient.builder().value(value).build();
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