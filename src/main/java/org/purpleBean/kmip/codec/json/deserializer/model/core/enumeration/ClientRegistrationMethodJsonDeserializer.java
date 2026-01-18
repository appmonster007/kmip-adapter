package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodJsonDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType, String.class, value -> new ClientRegistrationMethod(ClientRegistrationMethod.fromName(value)));
    }
}