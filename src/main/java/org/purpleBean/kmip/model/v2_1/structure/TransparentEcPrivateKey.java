package org.purpleBean.kmip.model.v2_1.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipStructure;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;

@Data
@Builder(toBuilder = true)
public class TransparentEcPrivateKey implements KeyMaterial, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1);
  private static final KeyFormatType.Value keyFormatTypeValue =
      KeyFormatType.Standard.TRANSPARENT_EC_PRIVATE_KEY;

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, TransparentEcPrivateKey.class);
      KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentEcPrivateKey.class,
          TransparentEcPrivateKey::of);
    }
  }

  @NonNull
  private final RecommendedCurve recommendedCurve;
  @NonNull
  private final D d;

  @Builder
  private TransparentEcPrivateKey(
      @NonNull RecommendedCurve recommendedCurve,
      @NonNull D d
  ) {
    this.recommendedCurve = recommendedCurve;
    this.d = d;
    validate();
  }

  public static TransparentEcPrivateKey of(@NonNull KeyMaterial value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure.getValue())
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return TransparentEcPrivateKey.of(
        (RecommendedCurve) map
            .get(RecommendedCurve.kmipTag)
            .getFirst(),
        (D) map
            .get(D.kmipTag)
            .getFirst()
    );
  }

  public static TransparentEcPrivateKey of(@NonNull RecommendedCurve recommendedCurve,
                                           @NonNull D d) {
    return TransparentEcPrivateKey
        .builder()
        .recommendedCurve(recommendedCurve)
        .d(d)
        .build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return new KmipDataType[] {recommendedCurve, d};
  }
}