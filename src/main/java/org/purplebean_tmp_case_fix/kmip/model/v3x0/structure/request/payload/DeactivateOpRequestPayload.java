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
import org.purplebean.kmip.model.core.type.DeactivationDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;

/**
 * KMIP Deactivate Request Payload (V3_0).
 *
 * <p>Per KMIP v3.0 spec:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 *   <li>DeactivationReason — Optional</li>
 *   <li>DeactivationDate — Optional</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class DeactivateOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.DEACTIVATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          DeactivateOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, DeactivateOpRequestPayload.class,
          DeactivateOpRequestPayload::of);
    }
  }

  @NonNull
  private final UniqueIdentifier uniqueIdentifier;
  private final DeactivationReason deactivationReason;
  private final DeactivationDate deactivationDate;

  @Builder
  private DeactivateOpRequestPayload(
      @NonNull UniqueIdentifier uniqueIdentifier,
      DeactivationReason deactivationReason,
      DeactivationDate deactivationDate
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.deactivationReason = deactivationReason;
    this.deactivationDate = deactivationDate;
    validate();
  }

  public static DeactivateOpRequestPayload of(List<KmipDataType> values) {
    var builder = DeactivateOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      } else if (value instanceof DeactivationReason) {
        builder.deactivationReason((DeactivationReason) value);
      } else if (value instanceof DeactivationDate) {
        builder.deactivationDate((DeactivationDate) value);
      }
    });
    return builder.build();
  }

  public static DeactivateOpRequestPayload of(
      @NonNull UniqueIdentifier uniqueIdentifier,
      DeactivationReason deactivationReason,
      DeactivationDate deactivationDate
  ) {
    return DeactivateOpRequestPayload
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
        .deactivationReason(deactivationReason)
        .deactivationDate(deactivationDate)
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
        .of(
            uniqueIdentifier,
            deactivationReason,
            deactivationDate)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
