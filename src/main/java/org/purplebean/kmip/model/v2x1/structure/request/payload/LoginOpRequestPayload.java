package org.purplebean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * KMIP LoginOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class LoginOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.LOGIN;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, LoginOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, LoginOpRequestPayload.class,
          LoginOpRequestPayload::of);
    }
  }

  private final LeaseTime leaseTime;
  private final RequestCount requestCount;

  @Builder
  private LoginOpRequestPayload(LeaseTime leaseTime, RequestCount requestCount) {
    this.leaseTime = leaseTime;
    this.requestCount = requestCount;
    validate();
  }

  /**
   * Returns the {@link LoginOpRequestPayload} instance wrapping the given value.
   */
  public static LoginOpRequestPayload of(List<KmipDataType> values) {
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    var builder = LoginOpRequestPayload.builder();
    if (map.containsKey(LeaseTime.kmipTag)) {
      builder.leaseTime((LeaseTime) map
          .get(LeaseTime.kmipTag)
          .getFirst());
    }
    if (map.containsKey(RequestCount.kmipTag)) {
      builder.requestCount((RequestCount) map
          .get(RequestCount.kmipTag)
          .getFirst());
    }
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
    return supportedVersions.contains(KmipContext.getSpec());
  }

  @Override
  public KmipDataType[] getValue() {
    return Stream
        .of(leaseTime, requestCount)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
