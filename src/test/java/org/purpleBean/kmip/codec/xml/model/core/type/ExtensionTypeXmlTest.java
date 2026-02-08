package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ExtensionType XML Serialization Tests")
class ExtensionTypeXmlTest extends AbstractXmlSerializationTestSuite<ExtensionType> {

    @Override
    public Class<ExtensionType> type() {
        return ExtensionType.class;
    }

    @Override
    public ExtensionType createDefault() {
        return ExtensionType.builder().value(1).build();
    }

    @Override
    public ExtensionType createVariant() {
        return ExtensionType.builder().value(2).build();
    }
}