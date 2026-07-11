package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * §5.5 Attribute Reference — identifies an attribute either by vendor+name (custom attributes)
 * or by standard attribute tag. Fields are mutually exclusive: populate either
 * (vendorIdentification + attributeName) for vendor attributes, or consult the tag field
 * for standard attributes.
 */
@Data
@Builder(toBuilder = true)
public class AttributeReference implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE_REFERENCE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeReference.class);
        }
    }

    private final VendorIdentification vendorIdentification;
    private final AttributeName attributeName;

    @Builder
    private AttributeReference(VendorIdentification vendorIdentification, AttributeName attributeName) {
        this.vendorIdentification = vendorIdentification;
        this.attributeName = attributeName;
        validate();
    }

    public static AttributeReference ofVendor(@lombok.NonNull VendorIdentification vendorIdentification,
                                              @lombok.NonNull AttributeName attributeName) {
        return AttributeReference.builder()
                .vendorIdentification(vendorIdentification)
                .attributeName(attributeName)
                .build();
    }

    public static AttributeReference of(@lombok.NonNull KmipDataType value) {
        if (!(value instanceof KmipStructure structure)) throw new IllegalArgumentException("Invalid value: " + value);
        var builder = AttributeReference.builder();
        for (KmipDataType field : structure.getValue()) {
            if (field instanceof VendorIdentification v) builder.vendorIdentification(v);
            else if (field instanceof AttributeName n) builder.attributeName(n);
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(vendorIdentification, attributeName)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
