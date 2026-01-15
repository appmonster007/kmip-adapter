package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<KeyCompressionType, String> {

    public KeyCompressionTypeJsonDeserializer() {
        super(KeyCompressionType.kmipTag, KeyCompressionType.encodingType, String.class, value -> new KeyCompressionType(KeyCompressionType.fromName(value)));
    }
}