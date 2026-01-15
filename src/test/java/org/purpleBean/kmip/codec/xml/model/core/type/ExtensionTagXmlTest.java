package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ExtensionTag XML Serialization Tests")
class ExtensionTagXmlTest extends AbstractXmlSerializationTestSuite<ExtensionTag> {

    @Override
    protected Class<ExtensionTag> type() {
        return ExtensionTag.class;
    }

    @Override
    protected ExtensionTag createDefault() {
        return ExtensionTag.builder().value(1).build();
    }

    @Override
    protected ExtensionTag createVariant() {
        return ExtensionTag.builder().value(2).build();
    }
}