package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentEcmqvPrivateKey implements KeyMaterial.Structure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.Value.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_ECMQV_PRIVATE_KEY, TransparentEcmqvPrivateKey.class, TransparentEcmqvPrivateKey::of);
        }
    }

    @NonNull
    private final RecommendedCurve recommendedCurve;

    @NonNull
    private final D d;

    public static TransparentEcmqvPrivateKey of(@NonNull KeyMaterial.Value value) {
        if (!(value instanceof KeyMaterial.Structure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentEcmqvPrivateKey.of(
                (RecommendedCurve) map.get(RecommendedCurve.kmipTag).getFirst(),
                (D) map.get(D.kmipTag).getFirst()
        );
    }

    public static TransparentEcmqvPrivateKey of(@NonNull RecommendedCurve recommendedCurve, @NonNull D d) {
        return TransparentEcmqvPrivateKey.builder().recommendedCurve(recommendedCurve).d(d).build();
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

    public static class TransparentEcmqvPrivateKeyBuilder {
        public TransparentEcmqvPrivateKey build() {
            validate();
            return new TransparentEcmqvPrivateKey(recommendedCurve, d);
        }

        private void validate() {
            Objects.requireNonNull(recommendedCurve, "recommendedCurve cannot be null");
            Objects.requireNonNull(d, "d cannot be null");
        }
    }
}