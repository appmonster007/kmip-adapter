package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.NameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NameValue TTLV Serialization Tests")
class NameValueTtlvTest extends AbstractTtlvSerializationTestSuite<NameValue> {

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
