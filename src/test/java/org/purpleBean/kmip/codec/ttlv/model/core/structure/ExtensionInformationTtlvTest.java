package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionInformation Ttlv Serialization Tests")
class ExtensionInformationTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<ExtensionInformation> type() {
        return ExtensionInformation.class;
    }

    @Override
    public ExtensionInformation createDefault() {
        return ExtensionInformation.builder()
                .extensionName(ExtensionName.of("test-extension"))
                .build();
    }

    @Override
    public ExtensionInformation createVariant() {
        return ExtensionInformation.builder()
                .extensionName(ExtensionName.of("test-extension-variant"))
                .build();
    }
}