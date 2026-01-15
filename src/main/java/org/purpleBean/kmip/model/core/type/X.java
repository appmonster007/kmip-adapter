package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.math.BigInteger;
import java.util.Set;

/**
 * KMIP X dataType.
 */
@Data
@Builder(toBuilder = true)
public class X implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.X.inst();
    public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, X.class);
        }
    }


    @NonNull
    private final BigInteger value;

    public static X of(@NonNull BigInteger value) {
        return X.builder().value(value).build();
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