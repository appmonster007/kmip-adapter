package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> UniqueIdentifier.builder().value(value).build());
    }
}