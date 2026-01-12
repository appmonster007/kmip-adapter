package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodTtlvSerializer extends AbstractKmipTtlvSerializer<ClientRegistrationMethod, Integer> {

    public ClientRegistrationMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}