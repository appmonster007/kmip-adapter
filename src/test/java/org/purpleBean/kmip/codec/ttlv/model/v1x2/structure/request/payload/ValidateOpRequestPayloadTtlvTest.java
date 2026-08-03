package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.ValidateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidateOpRequestPayload Ttlv Serialization Tests")
class ValidateOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidateOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ValidateOpRequestPayload> type() {
    return ValidateOpRequestPayload.class;
  }

  @Override
  public ValidateOpRequestPayload createDefault() {
    return ValidateOpRequestPayload
        .builder()
        .certificate(Certificate.of(
            CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {1, 2, 3})))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityDate(ValidityDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
  }

  @Override
  public ValidateOpRequestPayload createVariant() {
    return ValidateOpRequestPayload
        .builder()
        .certificate(Certificate.of(
            CertificateType.Standard.X_509.inst(),
            CertificateValue.of(new byte[] {4, 5, 6})))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .validityDate(ValidityDate.of(OffsetDateTime
            .now(ZoneOffset.UTC)
            .plusDays(1)))
        .build();
  }
}
