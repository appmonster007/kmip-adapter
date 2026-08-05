package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.model.core.type.NonceValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AttestationCredential Domain Tests")
class AttestationCredentialTest extends AbstractKmipStructureTestSuite<AttestationCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AttestationCredential> type() {
    return AttestationCredential.class;
  }

  @Override
  protected AttestationCredential createDefault() {
    return AttestationCredential
        .builder()
        .nonce(Nonce.of(
            NonceId.of(new byte[8]),
            NonceValue.of(new byte[16])
        ))
        .attestationType(AttestationType.Standard.TPM_QUOTE.inst())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(Nonce.class);
    assertThat(values.get(1)).isInstanceOf(AttestationType.class);
  }
}