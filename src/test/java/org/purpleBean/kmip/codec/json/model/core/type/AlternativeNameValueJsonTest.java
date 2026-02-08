package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AlternativeNameValue JSON Serialization Tests")
class AlternativeNameValueJsonTest extends AbstractJsonSerializationTestSuite<AlternativeNameValue> {

    @Override
    public Class<AlternativeNameValue> type() {
        return AlternativeNameValue.class;
    }

    @Override
    public AlternativeNameValue createDefault() {
        return AlternativeNameValue.builder().value("some-value").build();
    }

    @Override
    public AlternativeNameValue createVariant() {
        return AlternativeNameValue.builder().value("some-other-value").build();
    }
}