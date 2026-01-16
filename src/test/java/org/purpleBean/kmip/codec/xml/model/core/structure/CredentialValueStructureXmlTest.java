package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CredentialValueStructure;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CredentialValueStructure Xml Serialization Tests")
class CredentialValueStructureXmlTest extends AbstractXmlSerializationTestSuite<CredentialValueStructure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CredentialValueStructure> type() {
        return CredentialValueStructure.class;
    }

    @Override
    protected CredentialValueStructure createDefault() {
        return CredentialValueStructure.builder()
                .value(Username.of("test-value"))
                .build();
    }

    @Override
    protected CredentialValueStructure createVariant() {
        return CredentialValueStructure.builder()
                .value(Username.of("test-value-variant"))
                .build();
    }
}