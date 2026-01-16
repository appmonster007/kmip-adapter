package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExtensionInformation Json Serialization Tests")
class ExtensionInformationJsonTest extends AbstractJsonSerializationTestSuite<ExtensionInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<ExtensionInformation> type() {
        return ExtensionInformation.class;
    }

    @Override
    protected ExtensionInformation createDefault() {
        return ExtensionInformation.builder()
                .extensionName(ExtensionName.of("test-extension"))
                .build();
    }

    @Override
    protected ExtensionInformation createVariant() {
        return ExtensionInformation.builder()
                .extensionName(ExtensionName.of("test-extension-variant"))
                .build();
    }
}