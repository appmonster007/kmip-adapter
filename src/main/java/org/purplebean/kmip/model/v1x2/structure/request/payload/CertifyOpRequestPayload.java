package org.purplebean.kmip.model.v1x2.structure.request.payload;

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
import org.purplebean.kmip.api.request.RequestPayloadStructure;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * KMIP CertifyOpRequestPayload operation request payload.
 */
@Data
@Builder(toBuilder = true)
public class CertifyOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.CERTIFY;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

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
  private final TemplateAttribute templateAttribute;

  @Builder
  private CertifyOpRequestPayload(
      UniqueIdentifier uniqueIdentifier,
      CertificateRequestType certificateRequestType,
      CertificateRequest certificateRequest,
      TemplateAttribute templateAttribute
  ) {
    this.uniqueIdentifier = uniqueIdentifier;
    this.certificateRequestType = certificateRequestType;
    this.certificateRequest = certificateRequest;
    this.templateAttribute = templateAttribute;
    validate();
  }

  /**
   * Returns the {@link CertifyOpRequestPayload} instance wrapping the given value.
   */
  public static CertifyOpRequestPayload of(
      UniqueIdentifier uniqueIdentifier,
      CertificateRequestType certificateRequestType,
      CertificateRequest certificateRequest,
      TemplateAttribute templateAttribute
  ) {
    return CertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(uniqueIdentifier)
        .certificateRequestType(certificateRequestType)
        .certificateRequest(certificateRequest)
        .templateAttribute(templateAttribute)
        .build();
  }

  /**
   * Returns the {@link CertifyOpRequestPayload} instance wrapping the given value.
   */
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
    if (map.containsKey(TemplateAttribute.kmipTag)) {
      builder.templateAttribute((TemplateAttribute) map
          .get(TemplateAttribute.kmipTag)
          .getFirst());
    }
    return builder.build();
  }

  private void validate() {
    if (!isSupported()) {
      throw new IllegalArgumentException(
          String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
    }
    // Add validation logic here
    if (certificateRequest != null && certificateRequestType == null) {
      throw new IllegalArgumentException(
          "Certificate Request Type is REQUIRED if Certificate Request is present.");
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
            uniqueIdentifier,
            certificateRequestType,
            certificateRequest,
            templateAttribute)
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