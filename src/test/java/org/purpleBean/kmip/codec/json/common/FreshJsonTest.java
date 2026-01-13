package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Fresh;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Fresh JSON Serialization Tests")
class FreshJsonTest extends AbstractJsonSerializationTestSuite<Fresh> {

    @Override
    protected Class<Fresh> type() {
        return Fresh.class;
    }

    @Override
    protected Fresh createDefault() {
        return Fresh.builder().value(true).build();
    }

    @Override
    protected Fresh createVariant() {
        return Fresh.builder().value(false).build();
    }
}