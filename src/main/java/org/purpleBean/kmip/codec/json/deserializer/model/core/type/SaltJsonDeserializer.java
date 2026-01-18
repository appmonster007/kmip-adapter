package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.nio.ByteBuffer;

public class SaltJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Salt, ByteBuffer> {

    public SaltJsonDeserializer() {
        super(Salt.kmipTag, Salt.encodingType, ByteBuffer.class, value -> Salt.builder().value(value).build());
    }
}