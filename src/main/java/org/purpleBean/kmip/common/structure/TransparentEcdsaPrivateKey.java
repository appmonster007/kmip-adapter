package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentEcdsaPrivateKey implements KeyMaterial, KmipStructure {
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_ECDSA_PRIVATE_KEY, TransparentEcdsaPrivateKey.class, TransparentEcdsaPrivateKey::of);
        }
    }

    @NonNull
    private final RecommendedCurve recommendedCurve;

    @NonNull
    private final D d;

    public static TransparentEcdsaPrivateKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentEcdsaPrivateKey.of(
                (RecommendedCurve) map.get(RecommendedCurve.kmipTag).getFirst(),
                (D) map.get(D.kmipTag).getFirst()
        );
    }

    public static TransparentEcdsaPrivateKey of(@NonNull RecommendedCurve recommendedCurve, @NonNull D d) {
        return TransparentEcdsaPrivateKey.builder().recommendedCurve(recommendedCurve).d(d).build();
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
        return List.of(recommendedCurve, d);
    }

    public static class TransparentEcdsaPrivateKeyBuilder {
        public TransparentEcdsaPrivateKey build() {
            validate();
            return new TransparentEcdsaPrivateKey(recommendedCurve, d);
        }

        private void validate() {
            Objects.requireNonNull(recommendedCurve, "recommendedCurve cannot be null");
            Objects.requireNonNull(d, "d cannot be null");
        }
    }
}