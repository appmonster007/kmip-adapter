package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("NameValue Domain Tests")
class NameValueTest extends AbstractKmipDataTypeSuite<NameValue> {

    @Override
    protected Class<NameValue> type() {
        return NameValue.class;
    }

    @Override
    protected NameValue createDefault() {
        return NameValue.of("some-name");
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}
