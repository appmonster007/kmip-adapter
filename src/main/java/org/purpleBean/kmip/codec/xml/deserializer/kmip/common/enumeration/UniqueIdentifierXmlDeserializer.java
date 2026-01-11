package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> new UniqueIdentifier(UniqueIdentifier.fromName(value)));
    }
}