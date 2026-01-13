package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ClientRegistrationMethod, Integer> {

    public ClientRegistrationMethodTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}