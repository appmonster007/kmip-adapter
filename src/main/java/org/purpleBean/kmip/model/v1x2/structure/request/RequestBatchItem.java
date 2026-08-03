package org.purpleBean.kmip.model.v1x2.structure.request;

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
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

@Data
@Builder(toBuilder = true)
public class RequestBatchItem implements RequestBatchItemStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestBatchItem.class);
      RequestBatchItemStructure.register(spec, RequestBatchItem.class, RequestBatchItem::of);
    }
  }

  @NonNull
  private final Operation operation;

  private final UniqueBatchItemID uniqueBatchItemID;

  @NonNull
  private final RequestPayloadStructure requestPayloadStructure;

  private final MessageExtension messageExtension;

  @Builder
  private RequestBatchItem(
      @NonNull Operation operation,
      UniqueBatchItemID uniqueBatchItemID,
      @NonNull RequestPayloadStructure requestPayloadStructure,
      MessageExtension messageExtension
  ) {
    this.operation = operation;
    this.uniqueBatchItemID = uniqueBatchItemID;
    this.requestPayloadStructure = requestPayloadStructure;
    this.messageExtension = messageExtension;
    validate();
  }

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
    if (map.containsKey(UniqueBatchItemID.kmipTag)) {
      builder.uniqueBatchItemID((UniqueBatchItemID) map
          .get(UniqueBatchItemID.kmipTag)
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
    Objects.requireNonNull(operation, "Operation cannot be null");
    Objects.requireNonNull(requestPayloadStructure, "RequestPayloadStructure cannot be null");
    if (!requestPayloadStructure
        .getCorrespondingOperation()
        .equals(operation)) {
      throw new IllegalArgumentException("Operation and RequestPayloadStructure do not match");
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
            requestPayloadStructure,
            messageExtension)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}