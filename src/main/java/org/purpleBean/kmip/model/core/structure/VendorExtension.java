package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.vendor.TtlvDataType;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class VendorExtension implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.VENDOR_EXTENSION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, VendorExtension.class);
        }
    }

    private final TtlvDataType ttlvDataType;

    @Builder
    private VendorExtension(
            TtlvDataType ttlvDataType
    ) {
        this.ttlvDataType = ttlvDataType;
        validate();
    }

    public static VendorExtension of(
            TtlvDataType ttlvDataType
    ) {
        return VendorExtension.builder()
                .ttlvDataType(ttlvDataType)
                .build();
    }


    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // Add validation logic here
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
        return supportedVersions.contains(spec) ;
    }

    @Override
    public List<KmipDataType> getValue() {
        return Stream.of(ttlvDataType)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toList();
    }
}