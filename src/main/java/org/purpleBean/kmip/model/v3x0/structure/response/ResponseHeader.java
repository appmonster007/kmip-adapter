package org.purplebean.kmip.model.v3x0.structure.response;

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
import org.purplebean.kmip.api.response.ResponseHeaderStructure;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v2x1.type.ServerCorrelationValue;
import org.purplebean.kmip.model.v3x0.type.ServerHashedPassword;

/**
 * KMIP 3.0 Response Header. Identical to the v2.1 Response Header (§8.2.2, v2.1) except
 * Batch Count was removed and Server Hashed Password was added in the 3.0 message framing
 * (§8.2.2, v3.0).
 */
@Data
@Builder(toBuilder = true)
public class ResponseHeader implements ResponseHeaderStructure {

  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResponseHeader.class);
      ResponseHeaderStructure.register(spec, ResponseHeader.class, ResponseHeader::of);
    }
  }

  @NonNull
  private final ProtocolVersion protocolVersion;

  @NonNull
  private final TimeStamp timeStamp;

  private final Nonce nonce;

  private final ServerHashedPassword serverHashedPassword;

  private final ServerCorrelationValue serverCorrelationValue;

  @Singular
  private final List<AttestationType> attestationTypes;

  @Builder
  private ResponseHeader(
      @NonNull ProtocolVersion protocolVersion,
      @NonNull TimeStamp timeStamp,
      Nonce nonce,
      ServerHashedPassword serverHashedPassword,
      ServerCorrelationValue serverCorrelationValue,
      List<AttestationType> attestationTypes
  ) {
    this.protocolVersion = protocolVersion;
    this.timeStamp = timeStamp;
    this.nonce = nonce;
    this.serverHashedPassword = serverHashedPassword;
    this.serverCorrelationValue = serverCorrelationValue;
    this.attestationTypes = attestationTypes;
    validate();
  }

  public static ResponseHeader of(List<KmipDataType> values) {
    var builder = ResponseHeader.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(ProtocolVersion.kmipTag)) {
      builder.protocolVersion((ProtocolVersion) map
          .get(ProtocolVersion.kmipTag)
          .getFirst());
    }
    if (map.containsKey(TimeStamp.kmipTag)) {
      builder.timeStamp((TimeStamp) map
          .get(TimeStamp.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Nonce.kmipTag)) {
      builder.nonce((Nonce) map
          .get(Nonce.kmipTag)
          .getFirst());
    }
    if (map.containsKey(ServerHashedPassword.kmipTag)) {
      builder.serverHashedPassword(
          (ServerHashedPassword) map
              .get(ServerHashedPassword.kmipTag)
              .getFirst());
    }
    if (map.containsKey(ServerCorrelationValue.kmipTag)) {
      builder.serverCorrelationValue(
          (ServerCorrelationValue) map
              .get(ServerCorrelationValue.kmipTag)
              .getFirst());
    }
    if (map.containsKey(AttestationType.kmipTag)) {
      map
          .get(AttestationType.kmipTag)
          .forEach(item -> builder.attestationType((AttestationType) item));
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
            protocolVersion,
            timeStamp,
            nonce,
            serverHashedPassword,
            serverCorrelationValue,
            attestationTypes)
        .filter(Objects::nonNull)
        .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        .map(KmipDataType.class::cast)
        .toArray(KmipDataType[]::new);
  }
}
