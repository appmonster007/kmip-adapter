package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InvocationFieldLength XML Serialization Tests")
class InvocationFieldLengthXmlTest extends AbstractXmlSerializationTestSuite<InvocationFieldLength> {

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