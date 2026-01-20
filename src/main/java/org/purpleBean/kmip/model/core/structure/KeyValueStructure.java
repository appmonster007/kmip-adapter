package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP KeyValueStructure structure.
 */
@Data
@Builder(toBuilder = true)
public class KeyValueStructure implements KeyValue, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyValueStructure.class);
        }
    }

    @NonNull
    private final KeyMaterial keyMaterial;
    @NonNull
    @Singular
    private final List<KmipAttribute> attributes;

    @Builder
    private KeyValueStructure(@NonNull KeyMaterial keyMaterial, List<KmipAttribute> attributes) {
        this.keyMaterial = keyMaterial;
        this.attributes = (attributes == null) ? Collections.emptyList() : attributes;
        validate();
    }

    public static KeyValueStructure of(@NonNull KeyMaterial keyMaterial, @NonNull List<KmipAttribute> attributes) {
        return KeyValueStructure.builder().keyMaterial(keyMaterial).attributes(attributes).build();
    }

    private void validate() {
        isSupported();
        List<KmipDataType> fields = Stream.concat(
                Stream.of(keyMaterial),
                attributes.stream()
        ).filter(Objects::nonNull).collect(Collectors.toList());

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s", field.getKmipTag().getDescription(), spec)
                );
            }
        }
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
        List<KmipDataType> fields = new ArrayList<>();
        fields.add(keyMaterial);
        fields.addAll(attributes);
        return fields.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}