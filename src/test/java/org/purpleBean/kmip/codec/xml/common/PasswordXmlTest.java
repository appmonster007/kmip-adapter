package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Password;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Password XML Serialization Tests")
class PasswordXmlTest extends AbstractXmlSerializationTestSuite<Password> {

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