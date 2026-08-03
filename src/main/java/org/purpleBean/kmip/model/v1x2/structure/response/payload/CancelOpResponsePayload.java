package org.purpleBean.kmip.model.v1x2.structure.response.payload;

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
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

@Data
@Builder(toBuilder = true)
public class CancelOpResponsePayload implements ResponsePayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CANCEL;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0,
          KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CancelOpResponsePayload.class);
      ResponsePayloadStructure.register(spec, operation, CancelOpResponsePayload.class,
          CancelOpResponsePayload::of);
    }
  }

  @NonNull
  private final AsynchronousCorrelationValue asynchronousCorrelationValue;

  @NonNull
  private final CancellationResult cancellationResult;

  @Builder
  private CancelOpResponsePayload(
      @NonNull AsynchronousCorrelationValue asynchronousCorrelationValue,
      @NonNull CancellationResult cancellationResult
  ) {
    this.asynchronousCorrelationValue = asynchronousCorrelationValue;
    this.cancellationResult = cancellationResult;
    validate();
  }

  public static CancelOpResponsePayload of(List<KmipDataType> values) {
    var builder = CancelOpResponsePayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(AsynchronousCorrelationValue.kmipTag)) {
      builder.asynchronousCorrelationValue((AsynchronousCorrelationValue) map
          .get(AsynchronousCorrelationValue.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CancellationResult.kmipTag)) {
      builder.cancellationResult((CancellationResult) map
          .get(CancellationResult.kmipTag)
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
            asynchronousCorrelationValue,
            cancellationResult)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
