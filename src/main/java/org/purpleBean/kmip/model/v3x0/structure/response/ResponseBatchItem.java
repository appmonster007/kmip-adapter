package org.purplebean.kmip.model.v3x0.structure.response;

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
import org.purplebean.kmip.api.response.ResponseBatchItemStructure;
import org.purplebean.kmip.api.response.ResponsePayloadStructure;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.enumeration.ResultReason;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.core.type.ResultMessage;

/**
 * KMIP 3.0 Response Batch Item. Identical to the v2.1 Response Batch Item (§8.2.3, v2.1) except
 * Unique Batch Item ID was removed in the 3.0 message framing (§8.2.3, v3.0).
 */
@Data
@Builder(toBuilder = true)
public class ResponseBatchItem implements ResponseBatchItemStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResponseBatchItem.class);
      ResponseBatchItemStructure.register(spec, ResponseBatchItem.class, ResponseBatchItem::of);
    }
  }

  private final Operation operation;

  private final ResultStatus resultStatus;

  private final ResultReason resultReason;

  private final ResultMessage resultMessage;

  private final AsynchronousCorrelationValue asynchronousCorrelationValue;

  private final ResponsePayloadStructure responsePayloadStructure;

  private final MessageExtension messageExtension;

  @Builder
  private ResponseBatchItem(
      Operation operation,
      ResultStatus resultStatus,
      ResultReason resultReason,
      ResultMessage resultMessage,
      AsynchronousCorrelationValue asynchronousCorrelationValue,
      ResponsePayloadStructure responsePayloadStructure,
      MessageExtension messageExtension
  ) {
    this.operation = operation;
    this.resultStatus = resultStatus;
    this.resultReason = resultReason;
    this.resultMessage = resultMessage;
    this.asynchronousCorrelationValue = asynchronousCorrelationValue;
    this.responsePayloadStructure = responsePayloadStructure;
    this.messageExtension = messageExtension;
    validate();
  }

  public static ResponseBatchItem of(List<KmipDataType> values) {
    var builder = ResponseBatchItem.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Operation.kmipTag)) {
      builder.operation((Operation) map
          .get(Operation.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ResultStatus.kmipTag)) {
      builder.resultStatus((ResultStatus) map
          .get(ResultStatus.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ResultReason.kmipTag)) {
      builder.resultReason((ResultReason) map
          .get(ResultReason.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ResultMessage.kmipTag)) {
      builder.resultMessage((ResultMessage) map
          .get(ResultMessage.kmipTag)
          .getFirst());
    }
    if (map.containsKey(AsynchronousCorrelationValue.kmipTag)) {
      builder.asynchronousCorrelationValue(
          (AsynchronousCorrelationValue) map
              .get(AsynchronousCorrelationValue.kmipTag)
              .getFirst());
    }
    if (map.containsKey(ResponsePayloadStructure.kmipTag)) {
      builder.responsePayloadStructure(
          (ResponsePayloadStructure) map
              .get(ResponsePayloadStructure.kmipTag)
              .getFirst());
    }
    if (map.containsKey(MessageExtension.kmipTag)) {
      builder.messageExtension((MessageExtension) map
          .get(MessageExtension.kmipTag)
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
            resultStatus,
            resultReason,
            resultMessage,
            asynchronousCorrelationValue,
            responsePayloadStructure,
            messageExtension)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public ResponsePayloadStructure getResponsePayload() {
    return responsePayloadStructure;
  }
}
