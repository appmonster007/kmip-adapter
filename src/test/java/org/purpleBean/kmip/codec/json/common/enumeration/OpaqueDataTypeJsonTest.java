package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("OpaqueDataType JSON Serialization")
class OpaqueDataTypeJsonTest extends AbstractJsonSerializationSuite<OpaqueDataType> {
    @Override
    protected Class<OpaqueDataType> type() {
        return OpaqueDataType.class;
    }

    @Override
    protected OpaqueDataType createDefault() {
        return new OpaqueDataType(OpaqueDataType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected OpaqueDataType createVariant() {
        return new OpaqueDataType(OpaqueDataType.Standard.PLACEHOLDER_2);
    }
}
