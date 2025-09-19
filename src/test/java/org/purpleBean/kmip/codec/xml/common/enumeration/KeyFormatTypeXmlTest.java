package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyFormatType XML Serialization")
class KeyFormatTypeXmlTest extends AbstractXmlSerializationSuite<KeyFormatType> {
    @Override
    protected Class<KeyFormatType> type() {
        return KeyFormatType.class;
    }

    @Override
    protected KeyFormatType createDefault() {
        return new KeyFormatType(KeyFormatType.Standard.RAW);
    }

    @Override
    protected KeyFormatType createVariant() {
        return new KeyFormatType(KeyFormatType.Standard.OPAQUE);
    }
}
