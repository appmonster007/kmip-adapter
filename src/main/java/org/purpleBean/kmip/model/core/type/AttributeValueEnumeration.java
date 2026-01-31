package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

@Data
@Builder(toBuilder = true)
public class AttributeValueEnumeration implements AttributeValue, KmipEnumeration {
    public static final KmipTag kmipTag = AttributeValue.kmipTag;
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValueEnumeration.class);
        }
    }

    @NonNull
    private final KmipEnumeration.Value<?> value;

    @Builder
    private AttributeValueEnumeration(@NonNull KmipEnumeration.Value<?> value) {
        this.value = value;
        validate();
    }

    public static AttributeValueEnumeration of(@NonNull KmipEnumeration.Value<?> value) {
        return new AttributeValueEnumeration(value);
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

    @Override
    public String getDescription() {
        return value.getDescription();
    }

    @Override
    public int getIntValue() {
        return value.getValue();
    }
}
