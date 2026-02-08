package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Password JSON Serialization Tests")
class PasswordJsonTest extends AbstractJsonSerializationTestSuite<Password> {

    @Override
    public Class<Password> type() {
        return Password.class;
    }

    @Override
    public Password createDefault() {
        return Password.builder().value("test-password").build();
    }

    @Override
    public Password createVariant() {
        return Password.builder().value("another-password").build();
    }
}