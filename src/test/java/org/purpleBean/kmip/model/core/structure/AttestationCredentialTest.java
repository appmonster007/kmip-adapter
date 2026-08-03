package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

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