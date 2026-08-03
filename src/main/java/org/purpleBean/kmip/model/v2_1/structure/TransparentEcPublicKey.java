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
import org.purpleBean.kmip.model.core.type.QString;

@Data
@Builder(toBuilder = true)
public class TransparentEcPublicKey implements KeyMaterial, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1);
  private static final KeyFormatType.Value keyFormatTypeValue =
      KeyFormatType.Standard.TRANSPARENT_EC_PUBLIC_KEY;

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, TransparentEcPublicKey.class);
      KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentEcPublicKey.class,
          TransparentEcPublicKey::of);
    }
  }

  @NonNull
  private final RecommendedCurve recommendedCurve;
  @NonNull
  private final QString qString;

  @Builder
  private TransparentEcPublicKey(
      @NonNull RecommendedCurve recommendedCurve,
      @NonNull QString qString
  ) {
    this.recommendedCurve = recommendedCurve;
    this.qString = qString;
    validate();
  }

  public static TransparentEcPublicKey of(@NonNull KeyMaterial value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure.getValue())
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return TransparentEcPublicKey.of(
        (RecommendedCurve) map
            .get(RecommendedCurve.kmipTag)
            .getFirst(),
        (QString) map
            .get(QString.kmipTag)
            .getFirst()
    );
  }

  public static TransparentEcPublicKey of(@NonNull RecommendedCurve recommendedCurve,
                                          @NonNull QString qString) {
    return TransparentEcPublicKey
        .builder()
        .recommendedCurve(recommendedCurve)
        .qString(qString)
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
    return new KmipDataType[] {recommendedCurve, qString};
  }
}