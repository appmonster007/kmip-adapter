package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.nio.ByteBuffer;

public class SaltTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Salt, ByteBuffer> {

    public SaltTtlvDeserializer() {
        super(Salt.kmipTag, Salt.encodingType, ByteBuffer.class, value -> Salt.builder().value(value).build());
    }
}