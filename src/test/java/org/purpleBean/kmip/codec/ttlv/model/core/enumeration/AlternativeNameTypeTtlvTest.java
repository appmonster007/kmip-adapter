package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeNameType TTLV Serialization")
class AlternativeNameTypeTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeNameType> {
    @Override
    public Class<AlternativeNameType> type() {
        return AlternativeNameType.class;
    }

    @Override
    public AlternativeNameType createDefault() {
        return AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    public AlternativeNameType createVariant() {
        return AlternativeNameType.Standard.URI.inst();
    }
}
