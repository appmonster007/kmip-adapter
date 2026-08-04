package org.purplebean.kmip.model.v2x1.structure.request.payload;

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
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;

/**
 * KMIP Pkcs11OpRequestPayload operation request payload.
 *
 * <p>Per KMIP v2.1/v3.0 spec §6.1.42:
 * <ul>
 *   <li>PKCS#11 Interface — Optional</li>
 *   <li>PKCS#11 Function — Required</li>
 *   <li>Correlation Value — Optional</li>
 *   <li>PKCS#11 Input Parameters — Optional</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11OpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.PKCS_11;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11OpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, Pkcs11OpRequestPayload.class,
          Pkcs11OpRequestPayload::of);
    }
  }

  private final Pkcs11Interface pkcs11Interface;

  @NonNull
  private final Pkcs11Function pkcs11Function;

  private final CorrelationValue correlationValue;

  private final Pkcs11InputParameters pkcs11InputParameters;

  @Builder
  private Pkcs11OpRequestPayload(
      Pkcs11Interface pkcs11Interface,
      @NonNull Pkcs11Function pkcs11Function,
      CorrelationValue correlationValue,
      Pkcs11InputParameters pkcs11InputParameters
  ) {
    this.pkcs11Interface = pkcs11Interface;
    this.pkcs11Function = pkcs11Function;
    this.correlationValue = correlationValue;
    this.pkcs11InputParameters = pkcs11InputParameters;
    validate();
  }

  /**
   * Returns the {@link Pkcs11OpRequestPayload} instance wrapping the given value.
   */
  public static Pkcs11OpRequestPayload of(List<KmipDataType> values) {
    var builder = Pkcs11OpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Pkcs11Interface.kmipTag)) {
      builder.pkcs11Interface((Pkcs11Interface) map
          .get(Pkcs11Interface.kmipTag)
          .getFirst());
    }
    builder.pkcs11Function((Pkcs11Function) map
        .get(Pkcs11Function.kmipTag)
        .getFirst());
    if (map.containsKey(CorrelationValue.kmipTag)) {
      builder.correlationValue((CorrelationValue) map
          .get(CorrelationValue.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Pkcs11InputParameters.kmipTag)) {
      builder.pkcs11InputParameters((Pkcs11InputParameters) map
          .get(Pkcs11InputParameters.kmipTag)
          .getFirst());
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
        .of(pkcs11Interface, pkcs11Function, correlationValue, pkcs11InputParameters)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
