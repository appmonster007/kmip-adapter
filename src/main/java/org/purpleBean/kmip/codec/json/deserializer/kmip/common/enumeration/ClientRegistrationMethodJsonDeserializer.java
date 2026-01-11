package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodJsonDeserializer extends AbstractKmipJsonDeserializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodJsonDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType, String.class, value -> new ClientRegistrationMethod(ClientRegistrationMethod.fromName(value)));
    }
}