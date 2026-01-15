package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("OpaqueDataValue Domain Tests")
class OpaqueDataValueTest extends AbstractKmipDataTypeTestSuite<OpaqueDataValue> {

    @Override
    protected Class<OpaqueDataValue> type() {
        return OpaqueDataValue.class;
    }

    @Override
    protected OpaqueDataValue createDefault() {
        return OpaqueDataValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}