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
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;

/**
 * KMIP SetEndpointRole Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>EndpointRole — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class SetEndpointRoleOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.SET_ENDPOINT_ROLE;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          SetEndpointRoleOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, SetEndpointRoleOpRequestPayload.class,
          SetEndpointRoleOpRequestPayload::of);
    }
  }

  @NonNull
  private final EndpointRole endpointRole;

  @Builder
  private SetEndpointRoleOpRequestPayload(@NonNull EndpointRole endpointRole) {
    this.endpointRole = endpointRole;
    validate();
  }

  public static SetEndpointRoleOpRequestPayload of(List<KmipDataType> values) {
    var builder = SetEndpointRoleOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof EndpointRole) {
        builder.endpointRole((EndpointRole) value);
      }
    });
    return builder.build();
  }

  public static SetEndpointRoleOpRequestPayload of(@NonNull EndpointRole endpointRole) {
    return SetEndpointRoleOpRequestPayload
        .builder()
        .endpointRole(endpointRole)
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
        .of(endpointRole)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
