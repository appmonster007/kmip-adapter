package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.OpaqueDataValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OpaqueDataValue XML Serialization Tests")
class OpaqueDataValueXmlTest extends AbstractXmlSerializationTestSuite<OpaqueDataValue> {

    @Override
    protected Class<OpaqueDataValue> type() {
        return OpaqueDataValue.class;
    }

    @Override
    protected OpaqueDataValue createDefault() {
        return OpaqueDataValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected OpaqueDataValue createVariant() {
        return OpaqueDataValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}