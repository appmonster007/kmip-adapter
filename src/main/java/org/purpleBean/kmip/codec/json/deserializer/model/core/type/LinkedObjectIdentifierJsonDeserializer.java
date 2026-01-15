package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierJsonDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType, String.class, value -> LinkedObjectIdentifier.builder().value(value).build());
    }
}