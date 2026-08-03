package org.purplebean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purplebean.kmip.model.v2x1.structure.Operations;

/**
 * KMIP QueryAsynchronousRequestsOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class QueryAsynchronousRequestsOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.QUERY_ASYNCHRONOUS_REQUESTS;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          QueryAsynchronousRequestsOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation,
          QueryAsynchronousRequestsOpRequestPayload.class,
          QueryAsynchronousRequestsOpRequestPayload::of);
    }
  }

  private final AsynchronousCorrelationValues asynchronousCorrelationValues;
  private final Operations operations;

  @Builder
  private QueryAsynchronousRequestsOpRequestPayload(
      AsynchronousCorrelationValues asynchronousCorrelationValues,
      Operations operations) {
    this.asynchronousCorrelationValues = asynchronousCorrelationValues;
    this.operations = operations;
    validate();
  }

  /**
   * Returns the {@link QueryAsynchronousRequestsOpRequestPayload} instance wrapping the given value.
   */
  public static QueryAsynchronousRequestsOpRequestPayload of(List<KmipDataType> values) {
    var builder = QueryAsynchronousRequestsOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof AsynchronousCorrelationValues v) {
        builder.asynchronousCorrelationValues(v);
      } else if (value instanceof Operations v) {
        builder.operations(v);
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
        .of(asynchronousCorrelationValues, operations)
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
