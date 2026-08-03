package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11OutputParameters;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

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
  private final Pkcs11ReturnCode pkcs11ReturnCode;

  private final Pkcs11OutputParameters pkcs11OutputParameters;

  @Builder
  private Pkcs11OpResponsePayload(
      @NonNull Pkcs11ReturnCode pkcs11ReturnCode,
      Pkcs11OutputParameters pkcs11OutputParameters
  ) {
    this.pkcs11ReturnCode = pkcs11ReturnCode;
    this.pkcs11OutputParameters = pkcs11OutputParameters;
    validate();
  }

  public static Pkcs11OpResponsePayload of(List<KmipDataType> values) {
    var builder = Pkcs11OpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    builder.pkcs11ReturnCode((Pkcs11ReturnCode) map
        .get(Pkcs11ReturnCode.kmipTag)
        .getFirst());
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
        .of(pkcs11ReturnCode, pkcs11OutputParameters)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
