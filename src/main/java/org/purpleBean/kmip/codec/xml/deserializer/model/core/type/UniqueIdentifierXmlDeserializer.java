package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierXmlDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> UniqueIdentifier.builder().value(value).build());
    }
}