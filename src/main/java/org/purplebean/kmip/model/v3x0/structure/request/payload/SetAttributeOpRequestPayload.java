package org.purplebean.kmip.model.v3x0.structure.request.payload;

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
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;

/**
 * KMIP SetAttribute Request Payload (V3_0).
 *
 * <p>Fork of
 * {@link org.purplebean.kmip.model.v2x1.structure.request.payload.SetAttributeOpRequestPayload}
 * for KMIP 3.0, where {@code UniqueIdentifier} becomes REQUIRED.
 *
 * <p>Per KMIP v3.0 spec §6.1.56:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 *   <li>NewAttribute — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class SetAttributeOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.SET_ATTRIBUTE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          SetAttributeOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, SetAttributeOpRequestPayload.class,
          SetAttributeOpRequestPayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final NewAttribute newAttribute;

  @Builder
  private SetAttributeOpRequestPayload(@NonNull UniqueIdentifier uniqueIdentifier,
                                       @NonNull NewAttribute newAttribute) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.newAttribute = newAttribute;
    validate();
  }

  /**
   * Returns the {@link SetAttributeOpRequestPayload} instance wrapping the given value.
   */
  public static SetAttributeOpRequestPayload of(List<KmipDataType> values) {
    var builder = SetAttributeOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      } else if (value instanceof NewAttribute) {
        builder.newAttribute((NewAttribute) value);
      }
    });
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
        .of(uniqueIdentifier, newAttribute)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
