package org.purpleBean.kmip.codec.ttlv.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateNameType TTLV Serialization")
class RotateNameTypeTtlvTest extends AbstractTtlvSerializationTestSuite<RotateNameType> {
    @Override
    public Class<RotateNameType> type() {
        return RotateNameType.class;
    }

    @Override
    public RotateNameType createDefault() {
        return RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    public RotateNameType createVariant() {
        return RotateNameType.Standard.URI.inst();
    }
}
