package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EndpointRole XML Serialization")
class EndpointRoleXmlTest extends AbstractXmlSerializationTestSuite<EndpointRole> {
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
