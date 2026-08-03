package org.purplebean.kmip.model.v2x1.structure.response.payload;

import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * KMIP SetConstraints Response Payload (V2_1).
 *
 * <p>Per KMIP v2.1 spec §6, this response payload defines no fields.
 */
@Data
@Builder(toBuilder = true)
public class SetConstraintsOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.SET_CONSTRAINTS;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          SetConstraintsOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, SetConstraintsOpResponsePayload.class,
          SetConstraintsOpResponsePayload::of);
    }
  }

  @Builder
  private SetConstraintsOpResponsePayload() {
    validate();
  }

  /**
   * Returns the {@link SetConstraintsOpResponsePayload} instance wrapping the given value.
   */
  public static SetConstraintsOpResponsePayload of(List<KmipDataType> values) {
    return SetConstraintsOpResponsePayload
        .builder()
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
    return supportedVersions.contains(KmipContext.getSpec());
  }

  @Override
  public KmipDataType[] getValue() {
    return new KmipDataType[0];
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
