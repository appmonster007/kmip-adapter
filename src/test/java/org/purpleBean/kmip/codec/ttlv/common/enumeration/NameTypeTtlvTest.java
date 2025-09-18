package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.NameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("NameType TTLV Serialization")
class NameTypeTtlvTest extends AbstractTtlvSerializationSuite<NameType> {
    @Override
    protected Class<NameType> type() {
        return NameType.class;
    }

    @Override
    protected NameType createDefault() {
        return new NameType(NameType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected NameType createVariant() {
        return new NameType(NameType.Standard.PLACEHOLDER_2);
    }
}
