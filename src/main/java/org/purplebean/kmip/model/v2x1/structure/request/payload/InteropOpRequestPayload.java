package org.purplebean.kmip.model.v2x1.structure.request.payload;

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
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

/**
 * KMIP Interop Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>InteropFunction — Required</li>
 *   <li>InteropIdentifier — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class InteropOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.INTEROP;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, InteropOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, InteropOpRequestPayload.class,
          InteropOpRequestPayload::of);
    }
  }

  @NonNull
  private final InteropFunction interopFunction;
  @NonNull
  private final InteropIdentifier interopIdentifier;

  @Builder
  private InteropOpRequestPayload(
      @NonNull InteropFunction interopFunction,
      @NonNull InteropIdentifier interopIdentifier
  ) {
    this.interopFunction = interopFunction;
    this.interopIdentifier = interopIdentifier;
    validate();
  }

  /**
   * Returns the {@link InteropOpRequestPayload} instance wrapping the given value.
   */
  public static InteropOpRequestPayload of(List<KmipDataType> values) {
    var builder = InteropOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof InteropFunction) {
        builder.interopFunction((InteropFunction) value);
      } else if (value instanceof InteropIdentifier) {
        builder.interopIdentifier((InteropIdentifier) value);
      }
    });
    return builder.build();
  }

  /**
   * Returns the {@link InteropOpRequestPayload} instance wrapping the given value.
   */
  public static InteropOpRequestPayload of(
      @NonNull InteropFunction interopFunction,
      @NonNull InteropIdentifier interopIdentifier
  ) {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(interopFunction)
        .interopIdentifier(interopIdentifier)
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
        .of(interopFunction, interopIdentifier)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
