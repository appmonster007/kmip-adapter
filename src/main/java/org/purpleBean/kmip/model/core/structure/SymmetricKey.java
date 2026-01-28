package org.purpleBean.kmip.model.core.structure;

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
public class SymmetricKey implements ManagedObject, KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.SYMMETRIC_KEY.inst();
    public static final ObjectType.Value objectTypeValue = ObjectType.Standard.SYMMETRIC_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SymmetricKey.class);
            ManagedObject.register(spec, kmipTag.getValue(), encodingType, objectTypeValue, SymmetricKey.class, SymmetricKey::of);
        }
    }

    @NonNull
    private final KeyBlock keyBlock;

    @Builder
    private SymmetricKey(
            @NonNull KeyBlock keyBlock
    ) {
        this.keyBlock = keyBlock;
        validate();
    }

    public static SymmetricKey of(
            @NonNull KeyBlock keyBlock
    ) {
        return SymmetricKey.builder()
                .keyBlock(keyBlock)
                .build();
    }

    public static SymmetricKey of(List<KmipDataType> values) {
        var builder = SymmetricKey.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(KeyBlock.kmipTag)) {
            builder.keyBlock((KeyBlock) map.get(KeyBlock.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(keyBlock, "KeyBlock cannot be null");
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
        return Stream.of(keyBlock)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public ObjectType getObjectType() {
        return objectTypeValue.inst();
    }
}
