package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

public class KeyCompressionTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeJsonSerializer() {
        super(KeyCompressionType::getDescription);
    }
}