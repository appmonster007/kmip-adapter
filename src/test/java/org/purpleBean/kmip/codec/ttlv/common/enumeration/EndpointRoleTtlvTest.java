package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("EndpointRole TTLV Serialization")
class EndpointRoleTtlvTest extends AbstractTtlvSerializationSuite<EndpointRole> {
    @Override
    protected Class<EndpointRole> type() {
        return EndpointRole.class;
    }

    @Override
    protected EndpointRole createDefault() {
        return new EndpointRole(EndpointRole.Standard.CLIENT);
    }

    @Override
    protected EndpointRole createVariant() {
        return new EndpointRole(EndpointRole.Standard.SERVER);
    }
}
