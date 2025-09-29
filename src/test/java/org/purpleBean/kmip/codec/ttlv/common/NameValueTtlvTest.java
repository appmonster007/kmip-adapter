package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.NameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("NameValue TTLV Serialization Tests")
class NameValueTtlvTest extends AbstractTtlvSerializationSuite<NameValue> {

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
