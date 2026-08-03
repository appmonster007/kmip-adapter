package org.purplebean.kmip.model.v2x1.structure.response.payload;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousRequest;

/**
 * KMIP QueryAsynchronousRequestsOpResponsePayload operation response payload.
 */
@Data
@Builder(toBuilder = true)
public class QueryAsynchronousRequestsOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.QUERY_ASYNCHRONOUS_REQUESTS;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          QueryAsynchronousRequestsOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation,
          QueryAsynchronousRequestsOpResponsePayload.class,
          QueryAsynchronousRequestsOpResponsePayload::of);
    }
  }

  @Singular
  private final List<AsynchronousRequest> asynchronousRequests;

  @Builder
  private QueryAsynchronousRequestsOpResponsePayload(
      List<AsynchronousRequest> asynchronousRequests) {
    this.asynchronousRequests =
        (asynchronousRequests == null) ? Collections.emptyList() : asynchronousRequests;
    validate();
  }

  /**
   * Returns the {@link QueryAsynchronousRequestsOpResponsePayload} instance wrapping the given
   * value.
   */
  public static QueryAsynchronousRequestsOpResponsePayload of(List<KmipDataType> values) {
    QueryAsynchronousRequestsOpResponsePayloadBuilder builder =
        QueryAsynchronousRequestsOpResponsePayload.builder();
    values
        .stream()
        .filter(v -> v instanceof AsynchronousRequest)
        .forEach(v -> builder.asynchronousRequest((AsynchronousRequest) v));
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
    return asynchronousRequests
        .stream()
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
