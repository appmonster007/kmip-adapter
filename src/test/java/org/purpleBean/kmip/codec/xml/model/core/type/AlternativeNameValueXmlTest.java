package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AlternativeNameValue XML Serialization Tests")
class AlternativeNameValueXmlTest extends AbstractXmlSerializationTestSuite<AlternativeNameValue> {

    @Override
    protected Class<AlternativeNameValue> type() {
        return AlternativeNameValue.class;
    }

    @Override
    protected AlternativeNameValue createDefault() {
        return AlternativeNameValue.builder().value("some-value").build();
    }

    @Override
    protected AlternativeNameValue createVariant() {
        return AlternativeNameValue.builder().value("some-other-value").build();
    }
}