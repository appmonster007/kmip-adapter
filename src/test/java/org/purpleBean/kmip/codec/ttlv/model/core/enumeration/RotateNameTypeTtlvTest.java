package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateNameType TTLV Serialization")
class RotateNameTypeTtlvTest extends AbstractTtlvSerializationTestSuite<RotateNameType> {
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
