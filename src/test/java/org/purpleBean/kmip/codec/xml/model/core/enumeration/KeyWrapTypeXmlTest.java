package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyWrapType XML Serialization")
class KeyWrapTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyWrapType> {
    @Override
    public Class<KeyWrapType> type() {
        return KeyWrapType.class;
    }

    @Override
    public KeyWrapType createDefault() {
        return KeyWrapType.Standard.NOT_WRAPPED.inst();
    }

    @Override
    public KeyWrapType createVariant() {
        return KeyWrapType.Standard.AS_REGISTERED.inst();
    }
}
