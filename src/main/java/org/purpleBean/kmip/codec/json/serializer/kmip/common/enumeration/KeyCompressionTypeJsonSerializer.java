package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeJsonSerializer extends AbstractKmipJsonSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeJsonSerializer() {
        super(KeyCompressionType::getDescription);
    }
}