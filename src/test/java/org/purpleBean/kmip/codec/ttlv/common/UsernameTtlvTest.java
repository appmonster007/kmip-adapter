package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Username;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Username TTLV Serialization Tests")
class UsernameTtlvTest extends AbstractTtlvSerializationTestSuite<Username> {

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