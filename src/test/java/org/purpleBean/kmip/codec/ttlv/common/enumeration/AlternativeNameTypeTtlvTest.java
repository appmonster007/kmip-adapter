package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AlternativeNameType TTLV Serialization")
class AlternativeNameTypeTtlvTest extends AbstractTtlvSerializationSuite<AlternativeNameType> {
    @Override
    protected Class<AlternativeNameType> type() {
        return AlternativeNameType.class;
    }

    @Override
    protected AlternativeNameType createDefault() {
        return new AlternativeNameType(AlternativeNameType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected AlternativeNameType createVariant() {
        return new AlternativeNameType(AlternativeNameType.Standard.PLACEHOLDER_2);
    }
}
