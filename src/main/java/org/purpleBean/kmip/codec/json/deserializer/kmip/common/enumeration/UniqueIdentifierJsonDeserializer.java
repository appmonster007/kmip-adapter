package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> new UniqueIdentifier(UniqueIdentifier.fromName(value)));
    }
}