package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.KeyMaterial;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class KeyMaterialStructure implements KeyMaterial.Structure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyMaterialStructure.class);
            KeyMaterial.Value.register(spec, encodingType, null, KeyMaterialStructure.class, KeyMaterialStructure::of);
        }
    }

    @NonNull
    private final List<KmipDataType> value;

    public static KeyMaterialStructure of(@NonNull KeyMaterial.Value value) {
        if (!(value instanceof KeyMaterialStructure keyMaterialStructure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        return keyMaterialStructure;
    }

    public static KeyMaterialStructure of(@NonNull List<KmipDataType> value) {
        return KeyMaterialStructure.builder().value(value).build();
    }

    public static KeyMaterialStructure of(@NonNull KmipDataType... values) {
        return of(List.of(values));
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
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return value.stream().filter(Objects::nonNull).toList();
    }
}
