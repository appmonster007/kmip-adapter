package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;

/**
 * KMIP TransparentEcdsaPublicKey attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class TransparentEcdsaPublicKey implements KeyMaterial, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);
  private static final KeyFormatType.Value keyFormatTypeValue =
      KeyFormatType.Standard.TRANSPARENT_ECDSA_PUBLIC_KEY;

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentEcdsaPublicKey.class,
          TransparentEcdsaPublicKey::of);
    }
  }

  @NonNull
  private final RecommendedCurve recommendedCurve;

  @NonNull
  private final QString qString;

  @Builder
  private TransparentEcdsaPublicKey(@NonNull RecommendedCurve recommendedCurve,
                                    @NonNull QString qString) {
    this.recommendedCurve = recommendedCurve;
    this.qString = qString;
    validate();
  }

  /**
   * Returns the {@link TransparentEcdsaPublicKey} instance wrapping the given value.
   */
  public static TransparentEcdsaPublicKey of(@NonNull KeyMaterial value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure.getValue())
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return TransparentEcdsaPublicKey.of(
        (RecommendedCurve) map
            .get(RecommendedCurve.kmipTag)
            .getFirst(),
        (QString) map
            .get(QString.kmipTag)
            .getFirst()
    );
  }

  /**
   * Returns the {@link TransparentEcdsaPublicKey} instance wrapping the given value.
   */
  public static TransparentEcdsaPublicKey of(@NonNull RecommendedCurve recommendedCurve,
                                             @NonNull QString qString) {
    return TransparentEcdsaPublicKey
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(recommendedCurve, qString)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

}
