package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Password;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("Password TTLV Serialization Tests")
class PasswordTtlvTest extends AbstractTtlvSerializationSuite<Password> {

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