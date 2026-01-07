package org.purpleBean.kmip.common;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.KmipDataType;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP FixedFieldLength dataType.
 */
@Data
@Builder(toBuilder = true)
public class FixedFieldLength implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FIXED_FIELD_LENGTH);
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, FixedFieldLength.class);
        }
    }

    @NonNull
    private final Integer value;

    public static FixedFieldLength of(@NonNull Integer value) {
        return FixedFieldLength.builder().value(value).build();
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