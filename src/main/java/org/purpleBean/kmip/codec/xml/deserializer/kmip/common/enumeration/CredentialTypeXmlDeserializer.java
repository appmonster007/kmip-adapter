package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeXmlDeserializer extends AbstractKmipXmlDeserializer<CredentialType, String> {

    public CredentialTypeXmlDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, String.class, value -> new CredentialType(CredentialType.fromName(value)));
    }
}