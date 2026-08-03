package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

@Data
@Builder(toBuilder = true)
public class MacVerifyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.MAC_VERIFY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          MacVerifyOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, MacVerifyOpRequestPayload.class,
          MacVerifyOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  private final CryptographicParameters cryptographicParameters;

  private final DataByteString data;

  private final MacData macData;

  @Builder
  private MacVerifyOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CryptographicParameters cryptographicParameters,
      DataByteString data,
      MacData macData
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.cryptographicParameters = cryptographicParameters;
    this.data = data;
    this.macData = macData;
    validate();
  }

  public static MacVerifyOpRequestPayload of(List<KmipDataType> values) {
    var builder = MacVerifyOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CryptographicParameters.kmipTag)) {
      builder.cryptographicParameters((CryptographicParameters) map
          .get(CryptographicParameters.kmipTag)
          .getFirst());
    }
    if (map.containsKey(DataByteString.kmipTag)) {
      builder.data((DataByteString) map
          .get(DataByteString.kmipTag)
          .getFirst());
    }
    if (map.containsKey(MacData.kmipTag)) {
      builder.macData((MacData) map
          .get(MacData.kmipTag)
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
        .of(
            uniqueIdentifier,
            cryptographicParameters,
            data,
            macData)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}