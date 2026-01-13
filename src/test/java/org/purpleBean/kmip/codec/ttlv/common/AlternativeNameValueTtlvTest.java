package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeNameValue TTLV Serialization Tests")
class AlternativeNameValueTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeNameValue> {

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