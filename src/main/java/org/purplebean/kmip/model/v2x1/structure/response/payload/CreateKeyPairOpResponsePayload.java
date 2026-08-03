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
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;

@Data
@Builder(toBuilder = true)
public class CreateKeyPairOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_KEY_PAIR;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateKeyPairOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, CreateKeyPairOpResponsePayload.class,
          CreateKeyPairOpResponsePayload::of);
    }
  }

  @NonNull
  private final PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier;
  @NonNull
  private final PublicKeyUniqueIdentifier publicKeyUniqueIdentifier;

  @Builder
  private CreateKeyPairOpResponsePayload(
      @NonNull PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
      @NonNull PublicKeyUniqueIdentifier publicKeyUniqueIdentifier
  ) {
    this.privateKeyUniqueIdentifier = privateKeyUniqueIdentifier;
    this.publicKeyUniqueIdentifier = publicKeyUniqueIdentifier;
    validate();
  }

  public static CreateKeyPairOpResponsePayload of(List<KmipDataType> values) {
    var builder = CreateKeyPairOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(PrivateKeyUniqueIdentifier.kmipTag)) {
      builder.privateKeyUniqueIdentifier((PrivateKeyUniqueIdentifier) map
          .get(PrivateKeyUniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(PublicKeyUniqueIdentifier.kmipTag)) {
      builder.publicKeyUniqueIdentifier((PublicKeyUniqueIdentifier) map
          .get(PublicKeyUniqueIdentifier.kmipTag)
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
        .of(privateKeyUniqueIdentifier, publicKeyUniqueIdentifier)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
