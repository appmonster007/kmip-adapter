package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("NameValue XML Serialization Tests")
class NameValueXmlTest extends AbstractXmlSerializationSuite<NameValue> {

    @Override
    protected Class<NameValue> type() {
        return NameValue.class;
    }

    @Override
    protected NameValue createDefault() {
        return NameValue.of("some-name");
    }

    @Override
    protected NameValue createVariant() {
        return NameValue.of("some-variant-name");
    }
}
