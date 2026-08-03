package org.purpleBean.kmip.model.v3_0.structure.response.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP CreateCredential Response Payload (V3_0).
 *
 * <p>Per KMIP v3.0 spec:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class CreateCredentialOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_CREDENTIAL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateCredentialOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, CreateCredentialOpResponsePayload.class,
          CreateCredentialOpResponsePayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @Builder
  private CreateCredentialOpResponsePayload(@NonNull UniqueIdentifier uniqueIdentifier) {
    this.uniqueIdentifier = uniqueIdentifier;
    validate();
  }

  public static CreateCredentialOpResponsePayload of(List<KmipDataType> values) {
    var builder = CreateCredentialOpResponsePayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      }
    });
    return builder.build();
  }

  public static CreateCredentialOpResponsePayload of(@NonNull UniqueIdentifier uniqueIdentifier) {
    return CreateCredentialOpResponsePayload
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
        .build();
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
        .of(uniqueIdentifier)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
