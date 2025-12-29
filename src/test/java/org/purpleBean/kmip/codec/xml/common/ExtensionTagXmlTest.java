package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ExtensionTag XML Serialization Tests")
class ExtensionTagXmlTest extends AbstractXmlSerializationSuite<ExtensionTag> {

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