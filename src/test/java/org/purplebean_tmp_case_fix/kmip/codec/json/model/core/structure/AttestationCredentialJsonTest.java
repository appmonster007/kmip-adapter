package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.structure.AttestationCredential;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.model.core.type.NonceValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttestationCredential Json Serialization Tests")
class AttestationCredentialJsonTest
    extends AbstractJsonSerializationTestSuite<AttestationCredential> {

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