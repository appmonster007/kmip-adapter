package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.Key;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentSymmetricKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_SYMMETRIC_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentSymmetricKey.class, TransparentSymmetricKey::of);
        }
    }

    @NonNull
    private final Key key;

    @Builder
    private TransparentSymmetricKey(@NonNull Key key) {
        this.key = key;
        validate();
    }

    public static TransparentSymmetricKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentSymmetricKey.of((Key) map.get(Key.kmipTag).getFirst());
    }

    public static TransparentSymmetricKey of(@NonNull Key key) {
        return TransparentSymmetricKey.builder().key(key).build();
    }

    private void validate() {
        Objects.requireNonNull(key, "key cannot be null");
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
        return List.of(key);
    }

}