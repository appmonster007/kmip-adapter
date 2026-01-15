package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ClientRegistrationMethod JSON Serialization")
class ClientRegistrationMethodJsonTest extends AbstractJsonSerializationTestSuite<ClientRegistrationMethod> {
    @Override
    protected Class<ClientRegistrationMethod> type() {
        return ClientRegistrationMethod.class;
    }

    @Override
    protected ClientRegistrationMethod createDefault() {
        return ClientRegistrationMethod.Standard.UNSPECIFIED.inst();
    }

    @Override
    protected ClientRegistrationMethod createVariant() {
        return ClientRegistrationMethod.Standard.SERVER_PRE_GENERATED.inst();
    }
}
