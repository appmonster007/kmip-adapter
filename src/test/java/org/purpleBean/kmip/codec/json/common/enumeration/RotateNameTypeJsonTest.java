package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RotateNameType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RotateNameType JSON Serialization")
class RotateNameTypeJsonTest extends AbstractJsonSerializationSuite<RotateNameType> {
    @Override
    protected Class<RotateNameType> type() {
        return RotateNameType.class;
    }

    @Override
    protected RotateNameType createDefault() {
        return new RotateNameType(RotateNameType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected RotateNameType createVariant() {
        return new RotateNameType(RotateNameType.Standard.URI);
    }
}
