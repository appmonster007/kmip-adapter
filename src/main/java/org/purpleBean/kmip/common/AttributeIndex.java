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
 * KMIP AttributeIndex dataType.
 */
@Data
@Builder
public class AttributeIndex implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_INDEX);
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    private final int value;

    public static AttributeIndex of(int index) {
        return AttributeIndex.builder().value(index).build();
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
