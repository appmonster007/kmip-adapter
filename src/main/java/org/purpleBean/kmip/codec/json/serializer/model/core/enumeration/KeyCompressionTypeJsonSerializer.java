package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;

public class KeyCompressionTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyCompressionType, String> {

    public KeyCompressionTypeJsonSerializer() {
        super(KeyCompressionType::getDescription);
    }
}