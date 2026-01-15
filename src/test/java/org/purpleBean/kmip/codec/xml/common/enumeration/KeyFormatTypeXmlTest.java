package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyFormatType XML Serialization")
class KeyFormatTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyFormatType> {
    @Override
    protected Class<KeyFormatType> type() {
        return KeyFormatType.class;
    }

    @Override
    protected KeyFormatType createDefault() {
        return KeyFormatType.Standard.RAW.inst();
    }

    @Override
    protected KeyFormatType createVariant() {
        return KeyFormatType.Standard.OPAQUE.inst();
    }
}
