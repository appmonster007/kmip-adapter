package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyValueLocationType, String> {

    public KeyValueLocationTypeJsonDeserializer() {
        super(KeyValueLocationType.kmipTag, KeyValueLocationType.encodingType, String.class, value -> new KeyValueLocationType(KeyValueLocationType.fromName(value)));
    }
}