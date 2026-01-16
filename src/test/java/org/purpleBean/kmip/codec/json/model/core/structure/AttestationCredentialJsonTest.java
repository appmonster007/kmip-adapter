package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.AttestationCredential;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttestationCredential Json Serialization Tests")
class AttestationCredentialJsonTest extends AbstractJsonSerializationTestSuite<AttestationCredential> {

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
        return AttestationCredential.builder()
                .nonce(Nonce.of(
                        NonceId.of(new byte[8]),
                        NonceValue.of(new byte[16])
                ))
                .attestationType(AttestationType.Standard.TPM_QUOTE.inst())
                .build();
    }

    @Override
    protected AttestationCredential createVariant() {
        return AttestationCredential.builder()
                .nonce(Nonce.of(
                        NonceId.of(new byte[8]),
                        NonceValue.of(new byte[8])
                ))
                .attestationType(AttestationType.Standard.SAML_ASSERTION.inst())
                .build();
    }
}