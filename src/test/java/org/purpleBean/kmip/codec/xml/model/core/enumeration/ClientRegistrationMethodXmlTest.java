package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ClientRegistrationMethod XML Serialization")
class ClientRegistrationMethodXmlTest extends AbstractXmlSerializationTestSuite<ClientRegistrationMethod> {
    @Override
    public Class<ClientRegistrationMethod> type() {
        return ClientRegistrationMethod.class;
    }

    @Override
    public ClientRegistrationMethod createDefault() {
        return ClientRegistrationMethod.Standard.UNSPECIFIED.inst();
    }

    @Override
    public ClientRegistrationMethod createVariant() {
        return ClientRegistrationMethod.Standard.SERVER_PRE_GENERATED.inst();
    }
}
