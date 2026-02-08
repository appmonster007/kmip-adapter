package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyFormatType XML Serialization")
class KeyFormatTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyFormatType> {
    @Override
    public Class<KeyFormatType> type() {
        return KeyFormatType.class;
    }

    @Override
    public KeyFormatType createDefault() {
        return KeyFormatType.Standard.RAW.inst();
    }

    @Override
    public KeyFormatType createVariant() {
        return KeyFormatType.Standard.OPAQUE.inst();
    }
}
