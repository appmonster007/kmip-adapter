package org.purplebean.kmip.model.v3x0.structure.request;

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
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;

/**
 * KMIP 3.0 Request Batch Item. Identical to the v2.1 Request Batch Item (§8.1.3, v2.1) except
 * Unique Batch Item ID was removed in the 3.0 message framing (§8.1.3, v3.0).
 */
@Data
@Builder(toBuilder = true)
public class RequestBatchItem implements RequestBatchItemStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestBatchItem.class);
      RequestBatchItemStructure.register(spec, RequestBatchItem.class, RequestBatchItem::of);
    }
  }

  private final Operation operation;

  private final Ephemeral ephemeral;

  private final AsynchronousCorrelationValue asynchronousCorrelationValue;

  private final RequestPayloadStructure requestPayloadStructure;

  private final MessageExtension messageExtension;

  @Builder
  private RequestBatchItem(
      Operation operation,
      Ephemeral ephemeral,
      AsynchronousCorrelationValue asynchronousCorrelationValue,
      RequestPayloadStructure requestPayloadStructure,
      MessageExtension messageExtension
  ) {
    this.operation = operation;
    this.ephemeral = ephemeral;
    this.asynchronousCorrelationValue = asynchronousCorrelationValue;
    this.requestPayloadStructure = requestPayloadStructure;
    this.messageExtension = messageExtension;
    validate();
  }

  /**
   * Returns the {@link RequestBatchItem} instance wrapping the given value.
   */
  public static RequestBatchItem of(List<KmipDataType> values) {
    var builder = RequestBatchItem.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Operation.kmipTag)) {
      builder.operation((Operation) map
          .get(Operation.kmipTag)
          .get(0));
    }
    if (map.containsKey(Ephemeral.kmipTag)) {
      builder.ephemeral((Ephemeral) map
          .get(Ephemeral.kmipTag)
          .get(0));
    }
    if (map.containsKey(AsynchronousCorrelationValue.kmipTag)) {
      builder.asynchronousCorrelationValue((AsynchronousCorrelationValue) map
          .get(AsynchronousCorrelationValue.kmipTag)
          .get(0));
    }
    if (map.containsKey(RequestPayloadStructure.kmipTag)) {
      builder.requestPayloadStructure((RequestPayloadStructure) map
          .get(RequestPayloadStructure.kmipTag)
          .get(0));
    }
    if (map.containsKey(MessageExtension.kmipTag)) {
      builder.messageExtension((MessageExtension) map
          .get(MessageExtension.kmipTag)
          .get(0));
    }
    return builder.build();
  }

  @Override
  public RequestPayloadStructure getRequestPayload() {
    return requestPayloadStructure;
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
        .of(
            operation,
            ephemeral,
            asynchronousCorrelationValue,
            requestPayloadStructure,
            messageExtension)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
