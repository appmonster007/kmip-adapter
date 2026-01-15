package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP UsageLimitsCount dataType.
 */
@Data
@Builder(toBuilder = true)
public class UsageLimitsCount implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.USAGE_LIMITS_COUNT.inst();
    public static final EncodingType encodingType = EncodingType.LONG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, UsageLimitsCount.class);
        }
    }


    @NonNull
    private final Long value;

    public static UsageLimitsCount of(@NonNull Long value) {
        return UsageLimitsCount.builder().value(value).build();
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