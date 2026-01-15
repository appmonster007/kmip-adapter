package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LeaseTime JSON Serialization Tests")
class LeaseTimeJsonTest extends AbstractJsonSerializationTestSuite<LeaseTime> {

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
