package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> new UniqueIdentifier(UniqueIdentifier.fromName(value)));
    }
}