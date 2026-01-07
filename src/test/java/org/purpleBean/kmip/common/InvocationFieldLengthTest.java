package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("InvocationFieldLength Domain Tests")
class InvocationFieldLengthTest extends AbstractKmipDataTypeSuite<InvocationFieldLength> {

    @Override
    protected Class<InvocationFieldLength> type() {
        return InvocationFieldLength.class;
    }

    @Override
    protected InvocationFieldLength createDefault() {
        return InvocationFieldLength.of(128);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}