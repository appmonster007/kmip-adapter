package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ExtensionName XML Serialization Tests")
class ExtensionNameXmlTest extends AbstractXmlSerializationTestSuite<ExtensionName> {

    @Override
    public Class<ExtensionName> type() {
        return ExtensionName.class;
    }

    @Override
    public ExtensionName createDefault() {
        return ExtensionName.builder().value("test-extension").build();
    }

    @Override
    public ExtensionName createVariant() {
        return ExtensionName.builder().value("another-extension").build();
    }
}