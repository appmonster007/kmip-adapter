package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NameType JSON Serialization")
class NameTypeJsonTest extends AbstractJsonSerializationTestSuite<NameType> {
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
