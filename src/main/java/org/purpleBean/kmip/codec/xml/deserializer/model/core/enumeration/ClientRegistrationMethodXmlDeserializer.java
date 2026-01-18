package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodXmlDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType, String.class, value -> new ClientRegistrationMethod(ClientRegistrationMethod.fromName(value)));
    }
}