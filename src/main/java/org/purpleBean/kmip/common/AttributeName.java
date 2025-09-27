package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;

import java.util.Set;

/**
 * KMIP AttributeName dataType.
 */
@Data
@Builder
public class AttributeName implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_NAME);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    @NonNull
    private final String value;

    public static AttributeName of(@NonNull String name) {
        return AttributeName.builder().value(name).build();
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
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }
}
