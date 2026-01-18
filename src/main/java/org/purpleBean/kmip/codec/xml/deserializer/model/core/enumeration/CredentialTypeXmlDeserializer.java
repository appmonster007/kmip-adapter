package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CredentialType, String> {

    public CredentialTypeXmlDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, String.class, value -> new CredentialType(CredentialType.fromName(value)));
    }
}