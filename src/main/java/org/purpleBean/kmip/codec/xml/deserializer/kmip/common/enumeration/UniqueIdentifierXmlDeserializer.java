package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> new UniqueIdentifier(UniqueIdentifier.fromName(value)));
    }
}