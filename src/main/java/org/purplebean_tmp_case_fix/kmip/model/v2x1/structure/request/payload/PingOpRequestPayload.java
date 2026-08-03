package org.purplebean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;

/**
 * KMIP Ping Request Payload (V2_1).
 *
 * <p>Per KMIP v2.1 spec §6, this request payload defines no fields.
 */
@Data
@Builder(toBuilder = true)
public class PingOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.PING;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PingOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, PingOpRequestPayload.class,
          PingOpRequestPayload::of);
    }
  }

  @Builder
  private PingOpRequestPayload() {
    validate();
  }

  public static PingOpRequestPayload of(List<KmipDataType> values) {
    return PingOpRequestPayload
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
