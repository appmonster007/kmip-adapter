package org.purplebean.kmip.model.v2x1.structure;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.core.type.CryptographicLength;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;

/**
 * KMIP RngParameters structure.
 */
@Data
@Builder(toBuilder = true)
public class RngParameters implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.RNG_PARAMETERS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RngParameters.class);
    }
  }

  private final RngAlgorithm rngAlgorithm;
  private final CryptographicAlgorithm cryptographicAlgorithm;
  private final CryptographicLength cryptographicLength;
  private final HashingAlgorithm hashingAlgorithm;
  private final DrbgAlgorithm drbgAlgorithm;
  private final RecommendedCurve recommendedCurve;
  private final Fips186Variation fips186Variation;
  private final PredictionResistance predictionResistance;

  @Builder
  private RngParameters(RngAlgorithm rngAlgorithm, CryptographicAlgorithm cryptographicAlgorithm,
                        CryptographicLength cryptographicLength, HashingAlgorithm hashingAlgorithm,
                        DrbgAlgorithm drbgAlgorithm, RecommendedCurve recommendedCurve,
                        Fips186Variation fips186Variation,
                        PredictionResistance predictionResistance) {
    this.rngAlgorithm = rngAlgorithm;
    this.cryptographicAlgorithm = cryptographicAlgorithm;
    this.cryptographicLength = cryptographicLength;
    this.hashingAlgorithm = hashingAlgorithm;
    this.drbgAlgorithm = drbgAlgorithm;
    this.recommendedCurve = recommendedCurve;
    this.fips186Variation = fips186Variation;
    this.predictionResistance = predictionResistance;
    validate();
  }

  /**
   * Returns the {@link RngParameters} instance wrapping the given value.
   */
  public static RngParameters of(@NonNull RngAlgorithm rngAlgorithm) {
    return RngParameters
        .builder()
        .rngAlgorithm(rngAlgorithm)
        .build();
  }

  /**
   * Returns the {@link RngParameters} instance wrapping the given value.
   */
  public static RngParameters of(@NonNull KmipDataType value) {
    if (!(value instanceof KmipStructure structure)) {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
    var builder = RngParameters.builder();
    for (KmipDataType field : structure.getValue()) {
      if (field instanceof RngAlgorithm a) {
        builder.rngAlgorithm(a);
      } else if (field instanceof CryptographicAlgorithm a) {
        builder.cryptographicAlgorithm(a);
      } else if (field instanceof CryptographicLength l) {
        builder.cryptographicLength(l);
      } else if (field instanceof HashingAlgorithm a) {
        builder.hashingAlgorithm(a);
      } else if (field instanceof DrbgAlgorithm a) {
        builder.drbgAlgorithm(a);
      } else if (field instanceof RecommendedCurve c) {
        builder.recommendedCurve(c);
      } else if (field instanceof Fips186Variation v) {
        builder.fips186Variation(v);
      } else if (field instanceof PredictionResistance p) {
        builder.predictionResistance(p);
      }
    }
    return builder.build();
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
    return Stream
        .of(rngAlgorithm, cryptographicAlgorithm, cryptographicLength,
            hashingAlgorithm, drbgAlgorithm, recommendedCurve, fips186Variation,
            predictionResistance)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }
}
