package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.AttestationCredential;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationCredential Ttlv Serialization Tests")
class AttestationCredentialTtlvTest
    extends AbstractTtlvSerializationTestSuite<AttestationCredential> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<AttestationCredential> type() {
    return AttestationCredential.class;
  }

  @Override
  public AttestationCredential createDefault() {
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
  public AttestationCredential createVariant() {
    return AttestationCredential
        .builder()
        .nonce(Nonce.of(
            NonceId.of(new byte[8]),
            NonceValue.of(new byte[8])
        ))
        .attestationType(AttestationType.Standard.SAML_ASSERTION.inst())
        .build();
  }
}