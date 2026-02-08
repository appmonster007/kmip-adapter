package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NameValue JSON Serialization Tests")
class NameValueJsonTest extends AbstractJsonSerializationTestSuite<NameValue> {

    @Override
    public Class<NameValue> type() {
        return NameValue.class;
    }

    @Override
    public NameValue createDefault() {
        return NameValue.of("some-name");
    }

    @Override
    public NameValue createVariant() {
        return NameValue.of("some-variant-name");
    }
}
