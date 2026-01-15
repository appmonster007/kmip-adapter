package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ClientRegistrationMethod, String> {

    public ClientRegistrationMethodXmlSerializer() {
        super(ClientRegistrationMethod::getDescription);
    }
}