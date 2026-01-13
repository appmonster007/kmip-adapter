package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodJsonSerializer() {
        super(ClientRegistrationMethod::getDescription);
    }
}