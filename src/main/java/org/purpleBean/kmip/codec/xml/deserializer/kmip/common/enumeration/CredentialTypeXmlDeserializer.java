package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CredentialType, String> {

    public CredentialTypeXmlDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, String.class, value -> new CredentialType(CredentialType.fromName(value)));
    }
}