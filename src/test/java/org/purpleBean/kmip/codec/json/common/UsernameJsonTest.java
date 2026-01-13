package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Username;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Username JSON Serialization Tests")
class UsernameJsonTest extends AbstractJsonSerializationTestSuite<Username> {

    @Override
    protected Class<Username> type() {
        return Username.class;
    }

    @Override
    protected Username createDefault() {
        return Username.builder().value("test-user").build();
    }

    @Override
    protected Username createVariant() {
        return Username.builder().value("another-user").build();
    }
}