package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class MessageExtension implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.MESSAGE_EXTENSION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, MessageExtension.class);
        }
    }

    private final VendorIdentification vendorIdentification;

    private final CriticalityIndicator criticalityIndicator;

    private final VendorExtension vendorExtension;

    @Builder
    private MessageExtension(
            VendorIdentification vendorIdentification,
            CriticalityIndicator criticalityIndicator,
            VendorExtension vendorExtension
    ) {
        this.vendorIdentification = vendorIdentification;
        this.criticalityIndicator = criticalityIndicator;
        this.vendorExtension = vendorExtension;
        validate();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation required for this structure
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        vendorIdentification,
                        criticalityIndicator,
                        vendorExtension)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}