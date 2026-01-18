package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, String> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType, String.class, value -> UniqueIdentifier.builder().value(value).build());
    }
}