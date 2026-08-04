package org.purplebean.kmip.model.v2x1.structure.response.payload;

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
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

/**
 * KMIP Pkcs11OpResponsePayload operation response payload.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11OpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.PKCS_11;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11OpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, Pkcs11OpResponsePayload.class,
          Pkcs11OpResponsePayload::of);
    }
  }

  @NonNull
  private final Pkcs11Function pkcs11Function;

  @NonNull
  private final Pkcs11ReturnCode pkcs11ReturnCode;

  private final Pkcs11OutputParameters pkcs11OutputParameters;

  @NonNull
  private final CorrelationValue correlationValue;

  @Builder
  private Pkcs11OpResponsePayload(
      @NonNull Pkcs11Function pkcs11Function,
      @NonNull Pkcs11ReturnCode pkcs11ReturnCode,
      Pkcs11OutputParameters pkcs11OutputParameters,
      @NonNull CorrelationValue correlationValue
  ) {
    this.pkcs11Function = pkcs11Function;
    this.pkcs11ReturnCode = pkcs11ReturnCode;
    this.pkcs11OutputParameters = pkcs11OutputParameters;
    this.correlationValue = correlationValue;
    validate();
  }

  /**
   * Returns the {@link Pkcs11OpResponsePayload} instance wrapping the given value.
   */
  public static Pkcs11OpResponsePayload of(List<KmipDataType> values) {
    var builder = Pkcs11OpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    builder.pkcs11Function((Pkcs11Function) map
        .get(Pkcs11Function.kmipTag)
        .getFirst());
    builder.pkcs11ReturnCode((Pkcs11ReturnCode) map
        .get(Pkcs11ReturnCode.kmipTag)
        .getFirst());
    if (map.containsKey(Pkcs11OutputParameters.kmipTag)) {
      builder.pkcs11OutputParameters((Pkcs11OutputParameters) map
          .get(Pkcs11OutputParameters.kmipTag)
          .getFirst());
    }
    builder.correlationValue((CorrelationValue) map
        .get(CorrelationValue.kmipTag)
        .getFirst());
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
        .of(pkcs11Function, pkcs11ReturnCode, pkcs11OutputParameters, correlationValue)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
