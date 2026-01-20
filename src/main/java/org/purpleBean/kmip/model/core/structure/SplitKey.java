package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SplitKey implements ManagedObject, KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.SPLIT_KEY.inst();
    public static final ObjectType.Value objectTypeValue = ObjectType.Standard.SPLIT_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SplitKey.class);
            ManagedObject.register(spec, encodingType, objectTypeValue, SplitKey.class, SplitKey::of);
        }
    }

    @NonNull
    private final SplitKeyParts splitKeyParts;

    @NonNull
    private final KeyPartIdentifier keyPartIdentifier;

    @NonNull
    private final SplitKeyThreshold splitKeyThreshold;

    @NonNull
    private final SplitKeyMethod splitKeyMethod;

    private final PrimeFieldSize primeFieldSize;

    @NonNull
    private final KeyBlock keyBlock;

    @Builder
    private SplitKey(
            @NonNull SplitKeyParts splitKeyParts,
            @NonNull KeyPartIdentifier keyPartIdentifier,
            @NonNull SplitKeyThreshold splitKeyThreshold,
            @NonNull SplitKeyMethod splitKeyMethod,
            PrimeFieldSize primeFieldSize,
            @NonNull KeyBlock keyBlock
    ) {
        this.splitKeyParts = splitKeyParts;
        this.keyPartIdentifier = keyPartIdentifier;
        this.splitKeyThreshold = splitKeyThreshold;
        this.splitKeyMethod = splitKeyMethod;
        this.primeFieldSize = primeFieldSize;
        this.keyBlock = keyBlock;
        validate();
    }

    public static SplitKey of(List<KmipDataType> values) {
        var builder = SplitKey.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(SplitKeyParts.kmipTag)) {
            builder.splitKeyParts((SplitKeyParts) map.get(SplitKeyParts.kmipTag).getFirst());
        }
        if (map.containsKey(KeyPartIdentifier.kmipTag)) {
            builder.keyPartIdentifier((KeyPartIdentifier) map.get(KeyPartIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKeyThreshold.kmipTag)) {
            builder.splitKeyThreshold((SplitKeyThreshold) map.get(SplitKeyThreshold.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKeyMethod.kmipTag)) {
            builder.splitKeyMethod((SplitKeyMethod) map.get(SplitKeyMethod.kmipTag).getFirst());
        }
        if (map.containsKey(PrimeFieldSize.kmipTag)) {
            builder.primeFieldSize((PrimeFieldSize) map.get(PrimeFieldSize.kmipTag).getFirst());
        }
        if (map.containsKey(KeyBlock.kmipTag)) {
            builder.keyBlock((KeyBlock) map.get(KeyBlock.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        isSupported();
        Objects.requireNonNull(splitKeyParts, "SplitKeyParts cannot be null");
        Objects.requireNonNull(keyPartIdentifier, "KeyPartIdentifier cannot be null");
        Objects.requireNonNull(splitKeyThreshold, "SplitKeyThreshold cannot be null");
        Objects.requireNonNull(splitKeyMethod, "SplitKeyMethod cannot be null");
        Objects.requireNonNull(keyBlock, "KeyBlock cannot be null");
        if (splitKeyMethod.equals(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_PRIME_FIELD.inst())
                && primeFieldSize == null) {
            throw new IllegalStateException("PrimeFieldSize is required for Split Key Method: POLYNOMIAL_SHARING_PRIME_FIELD");
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
        return Stream.of(
                        splitKeyParts,
                        keyPartIdentifier,
                        splitKeyThreshold,
                        splitKeyMethod,
                        primeFieldSize,
                        keyBlock)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
