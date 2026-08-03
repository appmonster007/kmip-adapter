package org.purpleBean.kmip.model.v2x1.structure.response.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP ReProvision Response Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec §6.1.48:
 * <ul>
 *   <li>UniqueIdentifier — Optional — the Certificate or Private Key unique identifier</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class ReProvisionOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.RE_PROVISION;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          ReProvisionOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, ReProvisionOpResponsePayload.class,
          ReProvisionOpResponsePayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;

  @Builder
  private ReProvisionOpResponsePayload(UniqueIdentifier uniqueIdentifier) {
    this.uniqueIdentifier = uniqueIdentifier;
    validate();
  }

  public static ReProvisionOpResponsePayload of(List<KmipDataType> values) {
    var builder = ReProvisionOpResponsePayload.builder();
    values.forEach(value -> {
      if (value instanceof UniqueIdentifier) {
        builder.uniqueIdentifier((UniqueIdentifier) value);
      }
    });
    return builder.build();
  }

  public static ReProvisionOpResponsePayload of(UniqueIdentifier uniqueIdentifier) {
    return ReProvisionOpResponsePayload
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
