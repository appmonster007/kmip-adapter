package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.util.Set;

@DisplayName("OpaqueDataType TTLV Serialization")
class OpaqueDataTypeTtlvTest extends AbstractTtlvSerializationSuite<OpaqueDataType> {
    @Override
    protected Class<OpaqueDataType> type() {
        return OpaqueDataType.class;
    }

    @Override
    protected OpaqueDataType createDefault() {
        return new OpaqueDataType(OpaqueDataType.register(0x80000000, "Custom", Set.of(KmipSpec.UnknownVersion)));
    }

    @Override
    protected OpaqueDataType createVariant() {
        return new OpaqueDataType(OpaqueDataType.register(0x80000001, "Custom2", Set.of(KmipSpec.UnknownVersion)));
    }
}
