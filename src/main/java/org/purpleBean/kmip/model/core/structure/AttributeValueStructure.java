package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class AttributeValueStructure implements AttributeValue, KmipStructure {
    public static final KmipTag kmipTag = AttributeValue.kmipTag;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValueStructure.class);
        }
    }

    @NonNull
    @Singular
    private final List<KmipDataType> values;

    @Builder
    private AttributeValueStructure(List<KmipDataType> values) {
        this.values = (values == null) ? Collections.emptyList() : values;
        validate();
    }

    public static AttributeValueStructure of(@NonNull List<KmipDataType> values) {
        return AttributeValueStructure.builder().values(values).build();
    }

    public static AttributeValueStructure of(@NonNull KmipDataType... values) {
        return of(List.of(values));
    }

    private void validate() {
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return values.stream().filter(Objects::nonNull).toList();
    }
}