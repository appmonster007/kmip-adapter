package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CredentialType, String> {

    public CredentialTypeXmlSerializer() {
        super(CredentialType::getDescription);
    }
}