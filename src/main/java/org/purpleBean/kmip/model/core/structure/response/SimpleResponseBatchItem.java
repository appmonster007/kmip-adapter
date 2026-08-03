package org.purpleBean.kmip.model.core.structure.response;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
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
import org.purpleBean.kmip.model.core.type.ResultMessage;

@Data
@Builder(toBuilder = true)
public class SimpleResponseBatchItem implements ResponseBatchItemStructure {

  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);

  static {
    KmipDataType.register(KmipSpec.UnknownVersion, kmipTag.getValue(), encodingType,
        SimpleResponseBatchItem.class);
    ResponseBatchItemStructure.register(KmipSpec.UnknownVersion, SimpleResponseBatchItem.class,
        SimpleResponseBatchItem::of);
  }

  private final Operation operation;

  @NonNull
  private final ResultStatus resultStatus;

  private final ResultReason resultReason;

  private final ResultMessage resultMessage;

  private final ResponsePayloadStructure responsePayloadStructure;

  @Builder
  private SimpleResponseBatchItem(
      Operation operation,
      @NonNull ResultStatus resultStatus,
      ResultReason resultReason,
      ResultMessage resultMessage,
      ResponsePayloadStructure responsePayloadStructure
  ) {
    this.operation = operation;
    this.resultStatus = resultStatus;
    this.resultReason = resultReason;
    this.resultMessage = resultMessage;
    this.responsePayloadStructure = responsePayloadStructure;
    validate();
  }

  public static SimpleResponseBatchItem of(KmipDataType... values) {
    return of(List.of(values));
  }

  public static SimpleResponseBatchItem of(List<KmipDataType> values) {
    var builder = SimpleResponseBatchItem.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(Operation.kmipTag)) {
      builder.operation((Operation) map
          .get(Operation.kmipTag)
          .get(0));
    }
    if (map.containsKey(ResultStatus.kmipTag)) {
      builder.resultStatus((ResultStatus) map
          .get(ResultStatus.kmipTag)
          .get(0));
    }
    if (map.containsKey(ResultReason.kmipTag)) {
      builder.resultReason((ResultReason) map
          .get(ResultReason.kmipTag)
          .get(0));
    }
    if (map.containsKey(ResultMessage.kmipTag)) {
      builder.resultMessage((ResultMessage) map
          .get(ResultMessage.kmipTag)
          .get(0));
    }
    if (map.containsKey(ResponsePayloadStructure.kmipTag)) {
      builder.responsePayloadStructure((ResponsePayloadStructure) map
          .get(ResponsePayloadStructure.kmipTag)
          .get(0));
    }
    return builder.build();
  }

  @Override
  public ResponsePayloadStructure getResponsePayload() {
    return responsePayloadStructure;
  }

  @Override
  public ResultMessage getResultMessage() {
    return resultMessage;
  }

  @Override
  public ResultReason getResultReason() {
    return resultReason;
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
  public KmipDataType[] getValue() {
    return Stream
        .of(operation, resultStatus, resultReason, resultMessage, responsePayloadStructure)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    return supportedVersions.contains(KmipContext.getSpec());
  }
}
