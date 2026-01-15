package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("EndpointRole TTLV Serialization")
class EndpointRoleTtlvTest extends AbstractTtlvSerializationTestSuite<EndpointRole> {
    @Override
    protected Class<EndpointRole> type() {
        return EndpointRole.class;
    }

    @Override
    protected EndpointRole createDefault() {
        return EndpointRole.Standard.CLIENT.inst();
    }

    @Override
    protected EndpointRole createVariant() {
        return EndpointRole.Standard.SERVER.inst();
    }
}
