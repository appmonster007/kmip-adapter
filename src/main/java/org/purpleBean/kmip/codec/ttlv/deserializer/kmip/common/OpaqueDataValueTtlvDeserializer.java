package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvDeserializer extends AbstractKmipTtlvDeserializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueTtlvDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType, ByteBuffer.class, value -> OpaqueDataValue.builder().value(value).build());
    }
}