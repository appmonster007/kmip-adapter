package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Fresh;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("Fresh JSON Serialization Tests")
class FreshJsonTest extends AbstractJsonSerializationSuite<Fresh> {

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