package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("OpaqueDataType XML Serialization")
class OpaqueDataTypeXmlTest extends AbstractXmlSerializationSuite<OpaqueDataType> {
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
