package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentEcdsaPublicKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_ECDSA_PUBLIC_KEY, TransparentEcdsaPublicKey.class, TransparentEcdsaPublicKey::of);
        }
    }

    @NonNull
    private final RecommendedCurve recommendedCurve;

    @NonNull
    private final QString qString;

    @Builder
    private TransparentEcdsaPublicKey(@NonNull RecommendedCurve recommendedCurve, @NonNull QString qString) {
        this.recommendedCurve = recommendedCurve;
        this.qString = qString;
        validate();
    }

    public static TransparentEcdsaPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentEcdsaPublicKey.of(
                (RecommendedCurve) map.get(RecommendedCurve.kmipTag).getFirst(),
                (QString) map.get(QString.kmipTag).getFirst()
        );
    }

    public static TransparentEcdsaPublicKey of(@NonNull RecommendedCurve recommendedCurve, @NonNull QString qString) {
        return TransparentEcdsaPublicKey.builder().recommendedCurve(recommendedCurve).qString(qString).build();
    }

    private void validate() {
        Objects.requireNonNull(recommendedCurve, "recommendedCurve cannot be null");
        Objects.requireNonNull(qString, "qString cannot be null");
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
        return List.of(recommendedCurve, qString);
    }
}