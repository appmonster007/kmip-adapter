package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CredentialValueGenericStructure Ttlv Serialization Tests")
class CredentialValueGenericStructureTtlvTest extends AbstractTtlvSerializationTestSuite<CredentialValueGenericStructure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CredentialValueGenericStructure> type() {
        return CredentialValueGenericStructure.class;
    }

    @Override
    protected CredentialValueGenericStructure createDefault() {
        return CredentialValueGenericStructure.builder()
                .value(Username.of("test-value"))
                .build();
    }

    @Override
    protected CredentialValueGenericStructure createVariant() {
        return CredentialValueGenericStructure.builder()
                .value(Username.of("test-value-variant"))
                .build();
    }
}