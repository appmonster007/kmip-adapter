package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ClientRegistrationMethod, Integer> {

    public ClientRegistrationMethodTtlvSerializer() {
        super(ClientRegistrationMethod::getValue);
    }
}