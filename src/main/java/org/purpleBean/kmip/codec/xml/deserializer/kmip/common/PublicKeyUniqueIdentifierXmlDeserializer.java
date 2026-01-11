package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType, String.class, value -> PublicKeyUniqueIdentifier.builder().value(value).build());
    }
}