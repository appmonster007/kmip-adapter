package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Set;

@DisplayName("OpaqueDataType XML Serialization")
class OpaqueDataTypeXmlTest extends AbstractXmlSerializationTestSuite<OpaqueDataType> {
    @Override
    public Class<OpaqueDataType> type() {
        return OpaqueDataType.class;
    }

    @Override
    public OpaqueDataType createDefault() {
        return OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    public OpaqueDataType createVariant() {
        return OpaqueDataType.register(0x80000001, "Custom2", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}
