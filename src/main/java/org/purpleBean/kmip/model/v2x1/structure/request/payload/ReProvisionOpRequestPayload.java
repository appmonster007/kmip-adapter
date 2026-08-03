package org.purpleBean.kmip.model.v2x1.structure.request.payload;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateRequest;

/**
 * KMIP ReProvision Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec §6.1.48:
 * <ul>
 *   <li>CertificateRequest — Optional — the certificate request (CSR) to be signed</li>
 *   <li>Certificate — Optional — the certificate to replace the existing one</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class ReProvisionOpRequestPayload implements RequestPayloadStructure {

  private static final Operation.Value operation = Operation.Standard.RE_PROVISION;
  private static final Set<KmipSpec> supportedVersions =
      Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

  static {
    for (KmipSpec spec : supportedVersions) {
      if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) {
        continue;
      }
      KmipDataType.register(spec, kmipTag.getValue(), encodingType,
          ReProvisionOpRequestPayload.class);
      RequestPayloadStructure.register(spec, operation, ReProvisionOpRequestPayload.class,
          ReProvisionOpRequestPayload::of);
    }
  }

  private final CertificateRequest certificateRequest;
  private final Certificate certificate;

  @Builder
  private ReProvisionOpRequestPayload(
      CertificateRequest certificateRequest,
      Certificate certificate
  ) {
    this.certificateRequest = certificateRequest;
    this.certificate = certificate;
    validate();
  }

  public static ReProvisionOpRequestPayload of(List<KmipDataType> values) {
    var builder = ReProvisionOpRequestPayload.builder();
    values.forEach(value -> {
      if (value instanceof CertificateRequest) {
        builder.certificateRequest((CertificateRequest) value);
      } else if (value instanceof Certificate) {
        builder.certificate((Certificate) value);
      }
    });
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
        .of(certificateRequest, certificate)
        .filter(Objects::nonNull)
        .map(kmipDataType -> kmipDataType)
        .toArray(KmipDataType[]::new);
  }

  @Override
  public Operation getCorrespondingOperation() {
    return operation.inst();
  }
}
