package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierJsonDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType, Integer.class, value -> KeyPartIdentifier.builder().value(value).build());
    }
}