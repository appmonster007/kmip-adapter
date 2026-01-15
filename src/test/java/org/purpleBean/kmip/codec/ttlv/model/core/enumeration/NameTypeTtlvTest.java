package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NameType TTLV Serialization")
class NameTypeTtlvTest extends AbstractTtlvSerializationTestSuite<NameType> {
    @Override
    protected Class<NameType> type() {
        return NameType.class;
    }

    @Override
    protected NameType createDefault() {
        return NameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    protected NameType createVariant() {
        return NameType.Standard.URI.inst();
    }
}
