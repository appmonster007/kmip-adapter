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
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;

/**
 * KMIP AdjustAttribute Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.0/v2.1/v3.0 spec §6.1.3:
 * <ul>
 *   <li>UniqueIdentifier — Optional</li>
 *   <li>AttributeReference — Required</li>
 *   <li>AdjustmentType — Required</li>
 *   <li>AdjustmentValue — Optional</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class AdjustAttributeOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.ADJUST_ATTRIBUTE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          AdjustAttributeOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, AdjustAttributeOpRequestPayload.class,
          AdjustAttributeOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @NonNull
  private final AttributeReference attributeReference;

  @NonNull
  private final AdjustmentType adjustmentType;

  private final AdjustmentValue adjustmentValue;

  @Builder
  private AdjustAttributeOpRequestPayload(UniqueIdentifier uniqueIdentifier,
                                          @NonNull AttributeReference attributeReference,
                                          @NonNull AdjustmentType adjustmentType,
                                          AdjustmentValue adjustmentValue) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.attributeReference = attributeReference;
    this.adjustmentType = adjustmentType;
    this.adjustmentValue = adjustmentValue;
    validate();
  }

  /**
   * Returns the {@link AdjustAttributeOpRequestPayload} instance wrapping the given value.
   */
  public static AdjustAttributeOpRequestPayload of(List<KmipDataType> values) {
    var builder = AdjustAttributeOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      } else if (value instanceof AttributeReference) {
        builder.attributeReference((AttributeReference) value);
      } else if (value instanceof AdjustmentType) {
        builder.adjustmentType((AdjustmentType) value);
      } else if (value instanceof AdjustmentValue) {
        builder.adjustmentValue((AdjustmentValue) value);
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
        .of(uniqueIdentifier, attributeReference, adjustmentType, adjustmentValue)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
