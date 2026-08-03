package org.purplebean.kmip.model.v2x1.structure.request;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.request.RequestBatchItemStructure;
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.api.request.RequestMessageStructure;

@Data
@Builder(toBuilder = true)
public class RequestMessage implements RequestMessageStructure {
  public static final KmipTag kmipTag = KmipTag.Standard.REQUEST_MESSAGE.inst();
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestMessage.class);
      RequestMessageStructure.register(spec, RequestMessage.class, RequestMessage::of);
    }
  }

  @NonNull
  private final RequestHeaderStructure requestHeader;
  @NonNull
  @Singular
  private final List<RequestBatchItemStructure> requestBatchItems;
  @NonNull
  @Singular
  private final List<Exception> requestBatchItemErrors;

  @Builder
  private RequestMessage(
      @NonNull RequestHeaderStructure requestHeader,
      List<RequestBatchItemStructure> requestBatchItems,
      List<Exception> requestBatchItemErrors
  ) {
    this.requestHeader = requestHeader;
    this.requestBatchItems =
        (requestBatchItems == null) ? Collections.emptyList() : requestBatchItems;
    this.requestBatchItemErrors =
        (requestBatchItemErrors == null) ? Collections.emptyList() : requestBatchItemErrors;
    validate();
  }

  public static RequestMessage of(List<KmipDataType> values, List<Exception> errors) {
    var builder = RequestMessage.builder();
    builder.requestBatchItemErrors(errors);
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(RequestHeaderStructure.kmipTag)) {
      builder.requestHeader((RequestHeaderStructure) map
          .get(RequestHeaderStructure.kmipTag)
          .get(0));
    }
    if (map.containsKey(RequestBatchItemStructure.kmipTag)) {
      builder.requestBatchItems(map
          .get(RequestBatchItemStructure.kmipTag)
          .stream()
          .map(e -> (RequestBatchItemStructure) e)
          .collect(Collectors.toList()));
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    if (requestBatchItems.size() != requestBatchItemErrors.size()) {
      throw new IllegalArgumentException(
          "requestBatchItems and requestBatchItemErrors must have the same size");
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
        .of(requestHeader, requestBatchItems)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
