package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP CurrentAttribute structure (V2_1+, tag 0x42013C).
 *
 * <p>Wraps the current value of a single typed attribute, used in AdjustAttribute operations.
 * Replaces the v1.x Attribute structure for current-value identification in KMIP v2.1+.
 */
@Data
@Builder(toBuilder = true)
public class CurrentAttribute implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.CURRENT_ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CurrentAttribute.class);
        }
    }

    @NonNull
    private final KmipAttribute attribute;

    @Builder
    private CurrentAttribute(@NonNull KmipAttribute attribute) {
        this.attribute = attribute;
        validate();
    }

    public static CurrentAttribute of(List<KmipDataType> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("CurrentAttribute requires exactly one attribute child");
        }
        return CurrentAttribute.builder()
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
