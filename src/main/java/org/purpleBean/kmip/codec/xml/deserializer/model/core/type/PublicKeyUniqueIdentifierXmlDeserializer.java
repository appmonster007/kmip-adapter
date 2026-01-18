package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

public class PublicKeyUniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyUniqueIdentifier, String> {

    public PublicKeyUniqueIdentifierXmlDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType, String.class, value -> PublicKeyUniqueIdentifier.builder().value(value).build());
    }
}