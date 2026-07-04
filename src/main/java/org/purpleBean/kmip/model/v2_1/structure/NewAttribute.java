package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP NewAttribute structure (V2_1+, tag 0x42013D).
 *
 * <p>Wraps a single typed attribute child used in SetAttribute and AdjustAttribute operations.
 * Replaces the v1.x Attribute structure for set/modify operations in KMIP v2.1+.
 */
@Data
@Builder(toBuilder = true)
public class NewAttribute implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.NEW_ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, NewAttribute.class);
        }
    }

    @NonNull
    private final KmipAttribute attribute;

    @Builder
    private NewAttribute(@NonNull KmipAttribute attribute) {
        this.attribute = attribute;
        validate();
    }

    public static NewAttribute of(List<KmipDataType> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("NewAttribute requires exactly one attribute child");
        }
        return NewAttribute.builder()
                .attribute((KmipAttribute) values.get(0))
                .build();
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
        return new KmipDataType[]{(KmipDataType) attribute};
    }
}
