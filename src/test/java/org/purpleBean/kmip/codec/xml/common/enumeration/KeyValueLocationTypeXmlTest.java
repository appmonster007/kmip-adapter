package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyValueLocationType XML Serialization")
class KeyValueLocationTypeXmlTest extends AbstractXmlSerializationSuite<KeyValueLocationType> {
    @Override
    protected Class<KeyValueLocationType> type() {
        return KeyValueLocationType.class;
    }

    @Override
    protected KeyValueLocationType createDefault() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected KeyValueLocationType createVariant() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.URI);
    }
}
