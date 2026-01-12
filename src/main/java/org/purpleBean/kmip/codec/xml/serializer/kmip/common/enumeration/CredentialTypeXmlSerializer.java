package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.CredentialType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CredentialTypeXmlSerializer extends AbstractKmipXmlSerializer<CredentialType, String> {

    public CredentialTypeXmlSerializer() {
        super(CredentialType::getDescription);
    }
}