package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ObjectDefaults implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.OBJECT_DEFAULTS.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObjectDefaults.class);
        }
    }

    @NonNull
    private final ObjectType objectType;
    @NonNull
    private final Attributes attributes;

    @Builder
    private ObjectDefaults(@NonNull ObjectType objectType, @NonNull Attributes attributes) {
        this.objectType = objectType;
        this.attributes = attributes;
        validate();
    }

    public static ObjectDefaults of(@NonNull KmipDataType value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue())
                .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return ObjectDefaults.of(
                (ObjectType) map.get(ObjectType.kmipTag).getFirst(),
                (Attributes) map.get(Attributes.kmipTag).getFirst()
        );
    }

    public static ObjectDefaults of(@NonNull ObjectType objectType, @NonNull Attributes attributes) {
        return ObjectDefaults.builder().objectType(objectType).attributes(attributes).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(objectType, attributes)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
