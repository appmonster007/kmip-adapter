package org.purplebean.kmip.model.v1x2.structure.response.payload;

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
 * KMIP Poll Response Payload (V1_2, V1_3, V1_4, V2_0, V2_1, V3_0).
 *
 * <p>Per KMIP spec §6.1.43, this response payload defines no fields. If the polled
 * operation has not completed, the response SHALL contain no payload and a Result Status
 * of Pending. If the operation has completed, the response SHALL contain the appropriate
 * payload for the completed operation (dispatched under that operation's own Operation
 * value, not under Poll).
 */
@Data
@Builder(toBuilder = true)
public class PollOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.POLL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PollOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, PollOpResponsePayload.class,
          PollOpResponsePayload::of);
    }
  }

  @Builder
  private PollOpResponsePayload() {
    validate();
  }

  /**
   * Returns the {@link PollOpResponsePayload} instance wrapping the given value.
   */
  public static PollOpResponsePayload of(List<KmipDataType> values) {
    return PollOpResponsePayload
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
