package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringJsonSerializer() {
        super(KeyValueByteString::getValue);
    }
}