package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ClientRegistrationMethodXmlSerializer extends AbstractKmipXmlSerializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodXmlSerializer() {
        super(ClientRegistrationMethod::getDescription);
    }
}