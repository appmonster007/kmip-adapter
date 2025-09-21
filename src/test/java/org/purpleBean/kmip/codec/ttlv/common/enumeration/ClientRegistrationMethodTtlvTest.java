package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ClientRegistrationMethod TTLV Serialization")
class ClientRegistrationMethodTtlvTest extends AbstractTtlvSerializationSuite<ClientRegistrationMethod> {
    @Override
    protected Class<ClientRegistrationMethod> type() {
        return ClientRegistrationMethod.class;
    }

    @Override
    protected ClientRegistrationMethod createDefault() {
        return new ClientRegistrationMethod(ClientRegistrationMethod.Standard.UNSPECIFIED);
    }

    @Override
    protected ClientRegistrationMethod createVariant() {
        return new ClientRegistrationMethod(ClientRegistrationMethod.Standard.SERVER_PRE_GENERATED);
    }
}
