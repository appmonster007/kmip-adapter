package org.purpleBean.kmip.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.DigestedData;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

@Data
@Builder(toBuilder = true)
public class SignOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.SIGN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, SignOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, SignOpRequestPayload.class,
          SignOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final CryptographicParameters cryptographicParameters;
  private final DataByteString data;
  private final DigestedData digestedData;
  private final CorrelationValue correlationValue;
  private final InitIndicator initIndicator;
  private final FinalIndicator finalIndicator;

  @Builder
  private SignOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CryptographicParameters cryptographicParameters,
      DataByteString data,
      DigestedData digestedData,
      CorrelationValue correlationValue,
      InitIndicator initIndicator,
      FinalIndicator finalIndicator
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.cryptographicParameters = cryptographicParameters;
    this.data = data;
    this.digestedData = digestedData;
    this.correlationValue = correlationValue;
    this.initIndicator = initIndicator;
    this.finalIndicator = finalIndicator;
    validate();
  }

  public static SignOpRequestPayload of(List<KmipDataType> values) {
    var builder = SignOpRequestPayload.builder();
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
    if (map.containsKey(DigestedData.kmipTag)) {
      builder.digestedData((DigestedData) map
          .get(DigestedData.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CorrelationValue.kmipTag)) {
      builder.correlationValue((CorrelationValue) map
          .get(CorrelationValue.kmipTag)
          .getFirst());
    }
    if (map.containsKey(InitIndicator.kmipTag)) {
      builder.initIndicator((InitIndicator) map
          .get(InitIndicator.kmipTag)
          .getFirst());
    }
    if (map.containsKey(FinalIndicator.kmipTag)) {
      builder.finalIndicator((FinalIndicator) map
          .get(FinalIndicator.kmipTag)
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
            digestedData,
            correlationValue,
            initIndicator,
            finalIndicator)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
