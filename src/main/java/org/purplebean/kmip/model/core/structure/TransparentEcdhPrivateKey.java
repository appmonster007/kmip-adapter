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
import org.purplebean.kmip.model.core.type.D;

/**
 * KMIP TransparentEcdhPrivateKey attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class TransparentEcdhPrivateKey implements KeyMaterial, KmipStructure {
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);
  private static final KeyFormatType.Value keyFormatTypeValue =
      KeyFormatType.Standard.TRANSPARENT_ECDH_PRIVATE_KEY;

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentEcdhPrivateKey.class,
          TransparentEcdhPrivateKey::of);
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

  /**
   * Returns the {@link TransparentEcdhPrivateKey} instance wrapping the given value.
   */
  public static TransparentEcdhPrivateKey of(@NonNull KeyMaterial value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid key material: " + value);
    }
    Map<KmipTag, List<KmipDataType>> map = Stream
        .of(structure.getValue())
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    return TransparentEcdhPrivateKey.of(
        (RecommendedCurve) map
            .get(RecommendedCurve.kmipTag)
            .getFirst(),
        (D) map
            .get(D.kmipTag)
            .getFirst()
    );
  }

  /**
   * Returns the {@link TransparentEcdhPrivateKey} instance wrapping the given value.
   */
  public static TransparentEcdhPrivateKey of(@NonNull RecommendedCurve recommendedCurve,
                                             @NonNull D d) {
    return TransparentEcdhPrivateKey
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
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(recommendedCurve, d)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

}
