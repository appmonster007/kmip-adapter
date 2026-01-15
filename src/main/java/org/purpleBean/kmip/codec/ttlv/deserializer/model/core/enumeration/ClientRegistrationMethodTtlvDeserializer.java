package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ClientRegistrationMethod, Integer> {

    public ClientRegistrationMethodTtlvDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType, Integer.class, value -> new ClientRegistrationMethod(ClientRegistrationMethod.fromValue(value)));
    }
}