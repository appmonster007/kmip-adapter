package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Set;

@DisplayName("OpaqueObject Xml Serialization Tests")
class OpaqueObjectXmlTest extends AbstractXmlSerializationTestSuite<OpaqueObject> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<OpaqueObject> type() {
        return OpaqueObject.class;
    }

    @Override
    protected OpaqueObject createDefault() {
        return OpaqueObject.builder()
                .opaqueDataType(OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)).inst())
                .opaqueDataValue(OpaqueDataValue.of(new byte[0]))
                .build();
    }

    @Override
    protected OpaqueObject createVariant() {
        return OpaqueObject.builder()
                .opaqueDataType(OpaqueDataType.register(0x80000001, "Custom-2", Set.of(KmipSpec.UnknownVersion)).inst())
                .opaqueDataValue(OpaqueDataValue.of(new byte[1]))
                .build();
    }
}