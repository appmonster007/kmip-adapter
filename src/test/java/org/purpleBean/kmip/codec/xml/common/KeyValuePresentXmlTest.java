package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValuePresent;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValuePresent Xml Serialization Tests")
class KeyValuePresentXmlTest extends AbstractXmlSerializationTestSuite<KeyValuePresent> {

    @Override
    protected Class<KeyValuePresent> type() {
        return KeyValuePresent.class;
    }

    @Override
    protected KeyValuePresent createDefault() {
        return KeyValuePresent.of(Boolean.FALSE);
    }

    @Override
    protected KeyValuePresent createVariant() {
        return KeyValuePresent.of(Boolean.TRUE);
    }
}