package org.purplebean.kmip.model.v3x0.structure.response.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP CreateUser Response Payload (V3_0).
 *
 * <p>Per KMIP v3.0 spec:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class CreateUserOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CREATE_USER;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          CreateUserOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, CreateUserOpResponsePayload.class,
          CreateUserOpResponsePayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @Builder
  private CreateUserOpResponsePayload(@NonNull UniqueIdentifier uniqueIdentifier) {
    this.uniqueIdentifier = uniqueIdentifier;
    validate();
  }

  /**
   * Returns the {@link CreateUserOpResponsePayload} instance wrapping the given value.
   */
  public static CreateUserOpResponsePayload of(List<KmipDataType> values) {
    var builder = CreateUserOpResponsePayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      }
    });
    return builder.build();
  }

  /**
   * Returns the {@link CreateUserOpResponsePayload} instance wrapping the given value.
   */
  public static CreateUserOpResponsePayload of(@NonNull UniqueIdentifier uniqueIdentifier) {
    return CreateUserOpResponsePayload
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
