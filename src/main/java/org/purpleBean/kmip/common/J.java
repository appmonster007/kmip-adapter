package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.math.BigInteger;
import java.util.Set;

/**
 * KMIP J dataType.
 */
@Data
@Builder(toBuilder = true)
public class J implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.J);
    public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, J.class);
        }
    }


    @NonNull
    private final BigInteger value;

    public static J of(@NonNull BigInteger value) {
        return J.builder().value(value).build();
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