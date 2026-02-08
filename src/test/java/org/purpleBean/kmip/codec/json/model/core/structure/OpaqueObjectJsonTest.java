package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.model.core.structure.OpaqueObject;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Set;

@DisplayName("OpaqueObject Json Serialization Tests")
class OpaqueObjectJsonTest extends AbstractJsonSerializationTestSuite<OpaqueObject> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<OpaqueObject> type() {
        return OpaqueObject.class;
    }

    @Override
    public OpaqueObject createDefault() {
        return OpaqueObject.builder()
                .opaqueDataType(OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)).inst())
                .opaqueDataValue(OpaqueDataValue.of(new byte[0]))
                .build();
    }

    @Override
    public OpaqueObject createVariant() {
        return OpaqueObject.builder()
                .opaqueDataType(OpaqueDataType.register(0x80000001, "Custom-2", Set.of(KmipSpec.UnknownVersion)).inst())
                .opaqueDataValue(OpaqueDataValue.of(new byte[1]))
                .build();
    }
}