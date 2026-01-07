package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InvocationFieldLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("InvocationFieldLength XML Serialization Tests")
class InvocationFieldLengthXmlTest extends AbstractXmlSerializationSuite<InvocationFieldLength> {

    @Override
    protected Class<InvocationFieldLength> type() {
        return InvocationFieldLength.class;
    }

    @Override
    protected InvocationFieldLength createDefault() {
        return InvocationFieldLength.of(128);
    }

    @Override
    protected InvocationFieldLength createVariant() {
        return InvocationFieldLength.of(256);
    }
}