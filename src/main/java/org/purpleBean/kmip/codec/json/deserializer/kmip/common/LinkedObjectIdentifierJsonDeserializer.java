package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LinkedObjectIdentifier, String> {

    public LinkedObjectIdentifierJsonDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType, String.class, value -> LinkedObjectIdentifier.builder().value(value).build());
    }
}