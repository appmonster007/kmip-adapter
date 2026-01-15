package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RotateNameType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateNameType JSON Serialization")
class RotateNameTypeJsonTest extends AbstractJsonSerializationTestSuite<RotateNameType> {
    @Override
    protected Class<RotateNameType> type() {
        return RotateNameType.class;
    }

    @Override
    protected RotateNameType createDefault() {
        return RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    protected RotateNameType createVariant() {
        return RotateNameType.Standard.URI.inst();
    }
}
