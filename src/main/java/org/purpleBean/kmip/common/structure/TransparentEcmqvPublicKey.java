package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentEcmqvPublicKey implements KeyMaterial, KmipStructure {
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_ECMQV_PUBLIC_KEY, TransparentEcmqvPublicKey.class, TransparentEcmqvPublicKey::of);
        }
    }

    @NonNull
    private final RecommendedCurve recommendedCurve;

    @NonNull
    private final QString qString;

    public static TransparentEcmqvPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentEcmqvPublicKey.of(
                (RecommendedCurve) map.get(RecommendedCurve.kmipTag).getFirst(),
                (QString) map.get(QString.kmipTag).getFirst()
        );
    }

    public static TransparentEcmqvPublicKey of(@NonNull RecommendedCurve recommendedCurve, @NonNull QString qString) {
        return TransparentEcmqvPublicKey.builder().recommendedCurve(recommendedCurve).qString(qString).build();
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

    public static class TransparentEcmqvPublicKeyBuilder {
        public TransparentEcmqvPublicKey build() {
            validate();
            return new TransparentEcmqvPublicKey(recommendedCurve, qString);
        }

        private void validate() {
            Objects.requireNonNull(recommendedCurve, "recommendedCurve cannot be null");
            Objects.requireNonNull(qString, "qString cannot be null");
        }
    }
}