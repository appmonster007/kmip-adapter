package org.purplebean.kmip.model.v3x0.structure.request;

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
import org.purplebean.kmip.api.request.RequestHeaderStructure;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purplebean.kmip.model.core.structure.Authentication;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.AttestationCapableIndicator;
import org.purplebean.kmip.model.core.type.MaximumResponseSize;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;
import org.purplebean.kmip.model.v2x1.type.ClientCorrelationValue;
import org.purplebean.kmip.model.v2x1.type.ServerCorrelationValue;

/**
 * KMIP 3.0 Request Header. Identical to the v2.1 Request Header (§8.1.2, v2.1) except
 * Batch Count and Batch Order Option were removed in the 3.0 message framing (§8.1.2, v3.0).
 */
@Data
@Builder(toBuilder = true)
public class RequestHeader implements RequestHeaderStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestHeader.class);
      RequestHeaderStructure.register(spec, RequestHeader.class, RequestHeader::of);
    }
  }

  @NonNull
  private final ProtocolVersion protocolVersion;

  private final MaximumResponseSize maximumResponseSize;

  private final ClientCorrelationValue clientCorrelationValue;

  private final ServerCorrelationValue serverCorrelationValue;

  private final AsynchronousIndicator asynchronousIndicator;

  private final AttestationCapableIndicator attestationCapableIndicator;

  @Singular
  private final List<AttestationType> attestationTypes;

  private final Authentication authentication;

  private final BatchErrorContinuationOption batchErrorContinuationOption;

  private final TimeStamp timeStamp;

  @Builder
  private RequestHeader(
      @NonNull ProtocolVersion protocolVersion,
      MaximumResponseSize maximumResponseSize,
      ClientCorrelationValue clientCorrelationValue,
      ServerCorrelationValue serverCorrelationValue,
      AsynchronousIndicator asynchronousIndicator,
      AttestationCapableIndicator attestationCapableIndicator,
      List<AttestationType> attestationTypes,
      Authentication authentication,
      BatchErrorContinuationOption batchErrorContinuationOption,
      TimeStamp timeStamp
  ) {
    this.protocolVersion = protocolVersion;
    this.maximumResponseSize = maximumResponseSize;
    this.clientCorrelationValue = clientCorrelationValue;
    this.serverCorrelationValue = serverCorrelationValue;
    this.asynchronousIndicator = asynchronousIndicator;
    this.attestationCapableIndicator = attestationCapableIndicator;
    this.attestationTypes = attestationTypes;
    this.authentication = authentication;
    this.batchErrorContinuationOption = batchErrorContinuationOption;
    this.timeStamp = timeStamp;
    validate();
  }

  public static RequestHeader of(List<KmipDataType> values) {
    var builder = RequestHeader.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(ProtocolVersion.kmipTag)) {
      builder.protocolVersion((ProtocolVersion) map
          .get(ProtocolVersion.kmipTag)
          .get(0));
    }
    if (map.containsKey(MaximumResponseSize.kmipTag)) {
      builder.maximumResponseSize(
          (MaximumResponseSize) map
              .get(MaximumResponseSize.kmipTag)
              .get(0));
    }
    if (map.containsKey(ClientCorrelationValue.kmipTag)) {
      builder.clientCorrelationValue(
          (ClientCorrelationValue) map
              .get(ClientCorrelationValue.kmipTag)
              .get(0));
    }
    if (map.containsKey(ServerCorrelationValue.kmipTag)) {
      builder.serverCorrelationValue(
          (ServerCorrelationValue) map
              .get(ServerCorrelationValue.kmipTag)
              .get(0));
    }
    if (map.containsKey(AsynchronousIndicator.kmipTag)) {
      builder.asynchronousIndicator(
          (AsynchronousIndicator) map
              .get(AsynchronousIndicator.kmipTag)
              .get(0));
    }
    if (map.containsKey(AttestationCapableIndicator.kmipTag)) {
      builder.attestationCapableIndicator(
          (AttestationCapableIndicator) map
              .get(AttestationCapableIndicator.kmipTag)
              .get(0));
    }
    if (map.containsKey(AttestationType.kmipTag)) {
      builder.attestationTypes(map
          .get(AttestationType.kmipTag)
          .stream()
          .map(AttestationType.class::cast)
          .toList());
    }
    if (map.containsKey(Authentication.kmipTag)) {
      builder.authentication((Authentication) map
          .get(Authentication.kmipTag)
          .get(0));
    }
    if (map.containsKey(BatchErrorContinuationOption.kmipTag)) {
      builder.batchErrorContinuationOption(
          (BatchErrorContinuationOption) map
              .get(BatchErrorContinuationOption.kmipTag)
              .get(0));
    }
    if (map.containsKey(TimeStamp.kmipTag)) {
      builder.timeStamp((TimeStamp) map
          .get(TimeStamp.kmipTag)
          .get(0));
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
  public KmipDataType[] getValue() {
    return Stream
        .of(
            protocolVersion,
            maximumResponseSize,
            clientCorrelationValue,
            serverCorrelationValue,
            asynchronousIndicator,
            attestationCapableIndicator,
            attestationTypes,
            authentication,
            batchErrorContinuationOption,
            timeStamp)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public boolean isSupported() {
    KmipSpec spec = KmipContext.getSpec();
    return supportedVersions.contains(spec) && Stream
        .of(getValue())
        .allMatch(KmipDataType::isSupported);
  }
}
