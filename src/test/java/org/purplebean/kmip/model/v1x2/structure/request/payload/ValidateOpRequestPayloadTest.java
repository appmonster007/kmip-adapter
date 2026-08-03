package org.purplebean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.ValidityDate;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ValidateOpRequestPayload Domain Tests")
class ValidateOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<ValidateOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ValidateOpRequestPayload> type() {
    return ValidateOpRequestPayload.class;
  }

  @Override
  protected ValidateOpRequestPayload createDefault() {
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
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(Certificate.class);
    assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(2)).isInstanceOf(ValidityDate.class);
  }
}
