package org.purplebean.kmip.model.v3x0.structure.response.payload;

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
 * KMIP Obliterate Response Payload (V3_0).
 *
 * <p>Per KMIP v3.0 spec §6, this response payload defines no fields.
 */
@Data
@Builder(toBuilder = true)
public class ObliterateOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.OBLITERATE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          ObliterateOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, ObliterateOpResponsePayload.class,
          ObliterateOpResponsePayload::of);
    }
  }

  @Builder
  private ObliterateOpResponsePayload() {
    validate();
  }

  /**
   * Returns the {@link ObliterateOpResponsePayload} instance wrapping the given value.
   */
  public static ObliterateOpResponsePayload of(List<KmipDataType> values) {
    return ObliterateOpResponsePayload
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
