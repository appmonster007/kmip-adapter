package org.purplebean.kmip.model.core.structure;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipStructure;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.model.core.type.IterationCount;
import org.purplebean.kmip.model.core.type.Salt;

@Data
@Builder(toBuilder = true)
public class DerivationParameters implements KmipStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.DERIVATION_PARAMETERS.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DerivationParameters.class);
    }
  }

  private final CryptographicParameters cryptographicParameters;
  private final InitializationVector initializationVector;
  private final DerivationData derivationData;
  private final Salt salt;
  private final IterationCount iterationCount;

  @Builder
  private DerivationParameters(
      CryptographicParameters cryptographicParameters,
      InitializationVector initializationVector,
      DerivationData derivationData,
      Salt salt,
      IterationCount iterationCount
  ) {
    this.cryptographicParameters = cryptographicParameters;
    this.initializationVector = initializationVector;
    this.derivationData = derivationData;
    this.salt = salt;
    this.iterationCount = iterationCount;
    validate();
  }

  public static DerivationParameters of(List<KmipDataType> values) {
    var builder = DerivationParameters.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(CryptographicParameters.kmipTag)) {
      builder.cryptographicParameters((CryptographicParameters) map
          .get(CryptographicParameters.kmipTag)
          .getFirst());
    }
    if (map.containsKey(InitializationVector.kmipTag)) {
      builder.initializationVector((InitializationVector) map
          .get(InitializationVector.kmipTag)
          .getFirst());
    }
    if (map.containsKey(DerivationData.kmipTag)) {
      builder.derivationData((DerivationData) map
          .get(DerivationData.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Salt.kmipTag)) {
      builder.salt((Salt) map
          .get(Salt.kmipTag)
          .getFirst());
    }
    if (map.containsKey(IterationCount.kmipTag)) {
      builder.iterationCount((IterationCount) map
          .get(IterationCount.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // Add validation logic here
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
        .of(
            cryptographicParameters,
            initializationVector,
            derivationData,
            salt,
            iterationCount)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
