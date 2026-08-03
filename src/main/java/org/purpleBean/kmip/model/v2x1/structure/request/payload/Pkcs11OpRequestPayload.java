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
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;
import org.purplebean.kmip.model.v2x1.type.Pkcs11InputParameters;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

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

  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final Pkcs11Function pkcs11Function;

  private final Pkcs11InputParameters pkcs11InputParameters;

  private final Pkcs11OutputParameters pkcs11OutputParameters;

  @Builder
  private Pkcs11OpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      @NonNull Pkcs11Function pkcs11Function,
      Pkcs11InputParameters pkcs11InputParameters,
      Pkcs11OutputParameters pkcs11OutputParameters
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.pkcs11Function = pkcs11Function;
    this.pkcs11InputParameters = pkcs11InputParameters;
    this.pkcs11OutputParameters = pkcs11OutputParameters;
    validate();
  }

  public static Pkcs11OpRequestPayload of(List<KmipDataType> values) {
    var builder = Pkcs11OpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    builder.pkcs11Function((Pkcs11Function) map
        .get(Pkcs11Function.kmipTag)
        .getFirst());
    if (map.containsKey(Pkcs11InputParameters.kmipTag)) {
      builder.pkcs11InputParameters((Pkcs11InputParameters) map
          .get(Pkcs11InputParameters.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Pkcs11OutputParameters.kmipTag)) {
      builder.pkcs11OutputParameters((Pkcs11OutputParameters) map
          .get(Pkcs11OutputParameters.kmipTag)
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
        .of(uniqueIdentifier, pkcs11Function, pkcs11InputParameters, pkcs11OutputParameters)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
