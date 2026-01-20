package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP AttributeIndex dataType.
 */
@Data
@Builder(toBuilder = true)
public class AttributeIndex implements KmipDataType {
    public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_INDEX.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeIndex.class);
        }
    }

    private final int value;

    @Builder
    private AttributeIndex(int value) {
        this.value = value;
        validate();
    }

    public static AttributeIndex of(int index) {
        return AttributeIndex.builder().value(index).build();
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
