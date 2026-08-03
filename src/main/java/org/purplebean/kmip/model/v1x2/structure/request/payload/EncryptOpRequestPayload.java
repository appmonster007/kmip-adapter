package org.purplebean.kmip.model.v1x2.structure.request.payload;

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
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP EncryptOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class EncryptOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.ENCRYPT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, EncryptOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, EncryptOpRequestPayload.class,
          EncryptOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  private final CryptographicParameters cryptographicParameters;

  private final DataByteString data;

  private final IVCounterNonce ivCounterNonce;

  @Builder
  private EncryptOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CryptographicParameters cryptographicParameters,
      DataByteString data,
      IVCounterNonce ivCounterNonce
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.cryptographicParameters = cryptographicParameters;
    this.data = data;
    this.ivCounterNonce = ivCounterNonce;
    validate();
  }

  /**
   * Returns the {@link EncryptOpRequestPayload} instance wrapping the given value.
   */
  public static EncryptOpRequestPayload of(List<KmipDataType> values) {
    var builder = EncryptOpRequestPayload.builder();
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
    if (map.containsKey(IVCounterNonce.kmipTag)) {
      builder.ivCounterNonce((IVCounterNonce) map
          .get(IVCounterNonce.kmipTag)
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
            ivCounterNonce)
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