package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class AttributeValueBoolean implements AttributeValue {
    public static final KmipTag kmipTag = AttributeValue.kmipTag;
    public static final EncodingType encodingType = EncodingType.BOOLEAN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValueBoolean.class);
        }
    }

    @NonNull
    private final java.lang.Boolean value;

    public static AttributeValueBoolean of(@NonNull java.lang.Boolean value) {
        return AttributeValueBoolean.builder().value(value).build();
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
