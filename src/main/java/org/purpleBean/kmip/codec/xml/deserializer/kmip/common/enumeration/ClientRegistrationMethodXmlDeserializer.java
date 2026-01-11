package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodXmlDeserializer extends AbstractKmipXmlDeserializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodXmlDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType, String.class, value -> new ClientRegistrationMethod(ClientRegistrationMethod.fromName(value)));
    }
}