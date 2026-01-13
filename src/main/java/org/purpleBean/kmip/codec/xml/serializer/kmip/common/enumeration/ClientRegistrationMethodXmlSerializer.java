package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodXmlSerializer() {
        super(ClientRegistrationMethod::getDescription);
    }
}