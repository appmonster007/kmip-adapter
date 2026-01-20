package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Set;

@DisplayName("OpaqueDataType TTLV Serialization")
class OpaqueDataTypeTtlvTest extends AbstractTtlvSerializationTestSuite<OpaqueDataType> {
    @Override
    protected Class<OpaqueDataType> type() {
        return OpaqueDataType.class;
    }

    @Override
    protected OpaqueDataType createDefault() {
        return OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    protected OpaqueDataType createVariant() {
        return OpaqueDataType.register(0x80000001, "Custom2", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}
