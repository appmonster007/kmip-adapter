package org.purpleBean.kmip.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

@Data
@Builder(toBuilder = true)
public class CertifyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CERTIFY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertifyOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, CertifyOpRequestPayload.class,
          CertifyOpRequestPayload::of);
    }
  }

  private final UniqueIdentifier uniqueIdentifier;
  private final CertificateRequestType certificateRequestType;
  private final CertificateRequest certificateRequest;
  private final Attributes attributes;

  @Builder
  private CertifyOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CertificateRequestType certificateRequestType,
      CertificateRequest certificateRequest,
      Attributes attributes
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.certificateRequestType = certificateRequestType;
    this.certificateRequest = certificateRequest;
    this.attributes = attributes;
    validate();
  }

  public static CertifyOpRequestPayload of(List<KmipDataType> values) {
    var builder = CertifyOpRequestPayload.builder();
    Map<KmipTag, List<KmipDataType>> map = values
        .stream()
        .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    if (map.containsKey(UniqueIdentifier.kmipTag)) {
      builder.uniqueIdentifier((UniqueIdentifier) map
          .get(UniqueIdentifier.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CertificateRequestType.kmipTag)) {
      builder.certificateRequestType((CertificateRequestType) map
          .get(CertificateRequestType.kmipTag)
          .getFirst());
    }
    if (map.containsKey(CertificateRequest.kmipTag)) {
      builder.certificateRequest((CertificateRequest) map
          .get(CertificateRequest.kmipTag)
          .getFirst());
    }
    if (map.containsKey(Attributes.kmipTag)) {
      builder.attributes((Attributes) map
          .get(Attributes.kmipTag)
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
        .of(uniqueIdentifier, certificateRequestType, certificateRequest, attributes)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
