package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AlternativeNameType JSON Serialization")
class AlternativeNameTypeJsonTest extends AbstractJsonSerializationTestSuite<AlternativeNameType> {
    @Override
    protected Class<AlternativeNameType> type() {
        return AlternativeNameType.class;
    }

    @Override
    protected AlternativeNameType createDefault() {
        return AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    }

    @Override
    protected AlternativeNameType createVariant() {
        return AlternativeNameType.Standard.URI.inst();
    }
}
