package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LeaseTime;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("LeaseTime JSON Serialization Tests")
class LeaseTimeJsonTest extends AbstractJsonSerializationSuite<LeaseTime> {

    @Override
    protected Class<LeaseTime> type() {
        return LeaseTime.class;
    }

    @Override
    protected LeaseTime createDefault() {
        return LeaseTime.builder().value(10).build();
    }

    @Override
    protected LeaseTime createVariant() {
        return LeaseTime.builder().value(100).build();
    }
}
