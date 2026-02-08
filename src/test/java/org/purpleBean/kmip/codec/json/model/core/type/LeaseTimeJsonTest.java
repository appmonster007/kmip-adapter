package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LeaseTime JSON Serialization Tests")
class LeaseTimeJsonTest extends AbstractJsonSerializationTestSuite<LeaseTime> {

    @Override
    public Class<LeaseTime> type() {
        return LeaseTime.class;
    }

    @Override
    public LeaseTime createDefault() {
        return LeaseTime.builder().value(10).build();
    }

    @Override
    public LeaseTime createVariant() {
        return LeaseTime.builder().value(100).build();
    }
}
