package org.purpleBean.kmip.model.v2_1.structure.response;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.core.type.ResultMessage;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

@Data
@Builder(toBuilder = true)
public class ResponseBatchItem implements ResponseBatchItemStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1);

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

  private final UniqueBatchItemID uniqueBatchItemID;

  private final ResultStatus resultStatus;

  private final ResultReason resultReason;

  private final ResultMessage resultMessage;

  private final AsynchronousCorrelationValue asynchronousCorrelationValue;

  private final ResponsePayloadStructure responsePayloadStructure;

  private final MessageExtension messageExtension;

  @Builder
  private ResponseBatchItem(
      Operation operation,
      UniqueBatchItemID uniqueBatchItemID,
      ResultStatus resultStatus,
      ResultReason resultReason,
      ResultMessage resultMessage,
      AsynchronousCorrelationValue asynchronousCorrelationValue,
      ResponsePayloadStructure responsePayloadStructure,
      MessageExtension messageExtension
  ) {
    this.operation = operation;
    this.uniqueBatchItemID = uniqueBatchItemID;
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
    if (map.containsKey(UniqueBatchItemID.kmipTag)) {
      builder.uniqueBatchItemID(
          (UniqueBatchItemID) map
              .get(UniqueBatchItemID.kmipTag)
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
            uniqueBatchItemID,
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
