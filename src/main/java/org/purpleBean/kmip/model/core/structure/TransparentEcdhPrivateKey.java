package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentEcdhPrivateKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_ECDH_PRIVATE_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentEcdhPrivateKey.class, TransparentEcdhPrivateKey::of);
        }
    }

    @NonNull
    private final RecommendedCurve recommendedCurve;

    @NonNull
    private final D d;

    @Builder
    private TransparentEcdhPrivateKey(@NonNull RecommendedCurve recommendedCurve, @NonNull D d) {
        this.recommendedCurve = recommendedCurve;
        this.d = d;
        validate();
    }

    public static TransparentEcdhPrivateKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValue().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentEcdhPrivateKey.of(
                (RecommendedCurve) map.get(RecommendedCurve.kmipTag).getFirst(),
                (D) map.get(D.kmipTag).getFirst()
        );
    }

    public static TransparentEcdhPrivateKey of(@NonNull RecommendedCurve recommendedCurve, @NonNull D d) {
        return TransparentEcdhPrivateKey.builder().recommendedCurve(recommendedCurve).d(d).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(recommendedCurve, "recommendedCurve cannot be null");
        Objects.requireNonNull(d, "d cannot be null");
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
        return supportedVersions.contains(spec) && getValue().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValue() {
        return List.of(recommendedCurve, d);
    }

}