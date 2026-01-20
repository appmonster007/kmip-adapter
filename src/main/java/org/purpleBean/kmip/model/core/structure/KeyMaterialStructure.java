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
public class KeyMaterialStructure implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyMaterialStructure.class);
            KeyMaterial.register(spec, encodingType, null, KeyMaterialStructure.class, KeyMaterialStructure::of);
        }
    }

    @NonNull
    @Singular
    private final List<KmipDataType> values;

    @Builder
    private KeyMaterialStructure(List<KmipDataType> values) {
        this.values = (values == null) ? Collections.emptyList() : values;
        validate();
    }

    public static KeyMaterialStructure of(@NonNull KeyMaterial value) {
        if (!(value instanceof KeyMaterialStructure keyMaterialStructure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        return keyMaterialStructure;
    }

    public static KeyMaterialStructure of(@NonNull List<KmipDataType> values) {
        return KeyMaterialStructure.builder().values(values).build();
    }

    public static KeyMaterialStructure of(@NonNull KmipDataType... values) {
        return of(List.of(values));
    }

    private void validate() {
        isSupported();
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
        return spec != KmipSpec.UnsupportedVersion
                && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return values.stream().filter(Objects::nonNull).toList();
    }

}