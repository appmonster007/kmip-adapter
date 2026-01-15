package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Password JSON Serialization Tests")
class PasswordJsonTest extends AbstractJsonSerializationTestSuite<Password> {

    @Override
    protected Class<Password> type() {
        return Password.class;
    }

    @Override
    protected Password createDefault() {
        return Password.builder().value("test-password").build();
    }

    @Override
    protected Password createVariant() {
        return Password.builder().value("another-password").build();
    }
}