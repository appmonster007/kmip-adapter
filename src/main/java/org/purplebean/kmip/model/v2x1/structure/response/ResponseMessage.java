package org.purplebean.kmip.model.v2x1.structure.response;

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
import org.purplebean.kmip.api.response.ResponseBatchItemStructure;
import org.purplebean.kmip.api.response.ResponseHeaderStructure;
import org.purplebean.kmip.api.response.ResponseMessageStructure;

/**
 * KMIP ResponseMessage response structure.
 */
@Data
@Builder(toBuilder = true)
public class ResponseMessage implements ResponseMessageStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResponseMessage.class);
      ResponseMessageStructure.register(spec, ResponseMessage.class, ResponseMessage::of);
    }
  }

  @NonNull
  private final ResponseHeaderStructure responseHeader;

  @NonNull
  @Singular
  private final List<ResponseBatchItemStructure> responseBatchItems;

  @NonNull
  @Singular
  private final List<Exception> responseBatchItemErrors;

  @Builder
  private ResponseMessage(
      @NonNull ResponseHeaderStructure responseHeader,
      List<ResponseBatchItemStructure> responseBatchItems,
      List<Exception> responseBatchItemErrors
  ) {
    this.responseHeader = responseHeader;
    this.responseBatchItems =
        (responseBatchItems == null) ? Collections.emptyList() : responseBatchItems;
    this.responseBatchItemErrors =
        (responseBatchItemErrors == null) ? Collections.emptyList() : responseBatchItemErrors;
    validate();
  }

  /**
   * Returns the {@link ResponseMessage} instance wrapping the given value.
   */
  public static ResponseMessage of(List<KmipDataType> values, List<Exception> errors) {
    var builder = ResponseMessage.builder();
    builder.responseBatchItemErrors(errors);
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(ResponseHeaderStructure.kmipTag)) {
      builder.responseHeader((ResponseHeaderStructure) map
          .get(ResponseHeaderStructure.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ResponseBatchItemStructure.kmipTag)) {
      builder.responseBatchItems(
          map
              .get(ResponseBatchItemStructure.kmipTag)
              .stream()
              .map(ResponseBatchItemStructure.class::cast)
              .collect(Collectors.toList())
      );
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
        .concat(Stream.of(responseHeader), responseBatchItems.stream())
        .filter(Objects::nonNull)
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}