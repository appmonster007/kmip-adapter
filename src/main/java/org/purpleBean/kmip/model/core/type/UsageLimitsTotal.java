package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP UsageLimitsTotal dataType.
 */
@Data
@Builder(toBuilder = true)
public class UsageLimitsTotal implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.USAGE_LIMITS_TOTAL.inst();
    public static final EncodingType encodingType = EncodingType.LONG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, UsageLimitsTotal.class);
        }
    }


    @NonNull
    private final Long value;

    @Builder
    private UsageLimitsTotal(@NonNull Long value) {
        this.value = value;
        validate();
    }

    public static UsageLimitsTotal of(@NonNull Long value) {
        return new UsageLimitsTotal(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
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
