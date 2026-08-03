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

@Data
@Builder(toBuilder = true)
public class PutOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.PUT;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, PutOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, PutOpResponsePayload.class,
          PutOpResponsePayload::of);
    }
  }

  @Builder
  private PutOpResponsePayload() {
    validate();
  }

  public static PutOpResponsePayload of(List<KmipDataType> values) {
    return PutOpResponsePayload
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
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec);
  }

  @Override
  public KmipDataType[] getValue() {
    return new KmipDataType[] {};
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
