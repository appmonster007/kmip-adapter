package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueTtlvDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType, ByteBuffer.class, value -> OpaqueDataValue.builder().value(value).build());
    }
}