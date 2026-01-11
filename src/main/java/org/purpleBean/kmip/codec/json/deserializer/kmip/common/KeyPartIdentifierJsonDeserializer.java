package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.KeyPartIdentifier;

public class KeyPartIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<KeyPartIdentifier, Integer> {

    public KeyPartIdentifierJsonDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType, Integer.class, value -> KeyPartIdentifier.builder().value(value).build());
    }
}