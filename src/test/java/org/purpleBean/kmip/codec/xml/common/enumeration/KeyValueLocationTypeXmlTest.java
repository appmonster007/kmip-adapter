package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValueLocationType XML Serialization")
class KeyValueLocationTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyValueLocationType> {
    @Override
    protected Class<KeyValueLocationType> type() {
        return KeyValueLocationType.class;
    }

    @Override
    protected KeyValueLocationType createDefault() {
        return KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    protected KeyValueLocationType createVariant() {
        return KeyValueLocationType.Standard.URI.inst();
    }
}
