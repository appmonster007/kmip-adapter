package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ClientRegistrationMethod XML Serialization")
class ClientRegistrationMethodXmlTest extends AbstractXmlSerializationSuite<ClientRegistrationMethod> {
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
