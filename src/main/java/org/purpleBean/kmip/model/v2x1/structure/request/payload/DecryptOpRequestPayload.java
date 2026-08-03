package org.purpleBean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.DataValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.type.AuthenticatedEncryptionAdditionalData;
import org.purpleBean.kmip.model.v2x1.type.AuthenticatedEncryptionTag;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2x1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2x1.type.InitIndicator;

@Data
@Builder(toBuilder = true)
public class DecryptOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.DECRYPT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, DecryptOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, DecryptOpRequestPayload.class,
          DecryptOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final CryptographicParameters cryptographicParameters;
  private final DataValue data;
  private final IVCounterNonce ivCounterNonce;
  private final CorrelationValue correlationValue;
  private final InitIndicator initIndicator;
  private final FinalIndicator finalIndicator;
  private final AuthenticatedEncryptionAdditionalData authenticatedEncryptionAdditionalData;
  private final AuthenticatedEncryptionTag authenticatedEncryptionTag;

  @Builder
  private DecryptOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CryptographicParameters cryptographicParameters,
      DataValue data,
      IVCounterNonce ivCounterNonce,
      CorrelationValue correlationValue,
      InitIndicator initIndicator,
      FinalIndicator finalIndicator,
      AuthenticatedEncryptionAdditionalData authenticatedEncryptionAdditionalData,
      AuthenticatedEncryptionTag authenticatedEncryptionTag
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.cryptographicParameters = cryptographicParameters;
    this.data = data;
    this.ivCounterNonce = ivCounterNonce;
    this.correlationValue = correlationValue;
    this.initIndicator = initIndicator;
    this.finalIndicator = finalIndicator;
    this.authenticatedEncryptionAdditionalData = authenticatedEncryptionAdditionalData;
    this.authenticatedEncryptionTag = authenticatedEncryptionTag;
    validate();
  }

  public static DecryptOpRequestPayload of(List<KmipDataType> values) {
    var builder = DecryptOpRequestPayload.builder();
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
      builder.data((DataValue) map
          .get(DataByteString.kmipTag)
          .getFirst());
    }
    if (map.containsKey(IVCounterNonce.kmipTag)) {
      builder.ivCounterNonce((IVCounterNonce) map
          .get(IVCounterNonce.kmipTag)
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
    if (map.containsKey(AuthenticatedEncryptionAdditionalData.kmipTag)) {
      builder.authenticatedEncryptionAdditionalData((AuthenticatedEncryptionAdditionalData) map
          .get(AuthenticatedEncryptionAdditionalData.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AuthenticatedEncryptionTag.kmipTag)) {
      builder.authenticatedEncryptionTag((AuthenticatedEncryptionTag) map
          .get(AuthenticatedEncryptionTag.kmipTag)
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
            ivCounterNonce,
            correlationValue,
            initIndicator,
            finalIndicator,
            authenticatedEncryptionAdditionalData,
            authenticatedEncryptionTag)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
