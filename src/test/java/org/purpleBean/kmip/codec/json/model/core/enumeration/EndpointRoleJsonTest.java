package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EndpointRole JSON Serialization")
class EndpointRoleJsonTest extends AbstractJsonSerializationTestSuite<EndpointRole> {
    @Override
    public Class<EndpointRole> type() {
        return EndpointRole.class;
    }

    @Override
    public EndpointRole createDefault() {
        return EndpointRole.Standard.CLIENT.inst();
    }

    @Override
    public EndpointRole createVariant() {
        return EndpointRole.Standard.SERVER.inst();
    }
}
