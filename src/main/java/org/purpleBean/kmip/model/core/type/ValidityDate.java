package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * KMIP ValidityDate dataType.
 */
@Data
@Builder(toBuilder = true)
public class ValidityDate implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.VALIDITY_DATE.inst();
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ValidityDate.class);
        }
    }


    @NonNull
    private final OffsetDateTime value;

    public static ValidityDate of(@NonNull OffsetDateTime value) {
        return ValidityDate.builder().value(value).build();
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