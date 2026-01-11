package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PrivateKeyUniqueIdentifier;

public class PrivateKeyUniqueIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<PrivateKeyUniqueIdentifier, String> {

    public PrivateKeyUniqueIdentifierXmlDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType, String.class, value -> PrivateKeyUniqueIdentifier.builder().value(value).build());
    }
}