package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueJsonDeserializer extends AbstractKmipJsonDeserializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueJsonDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType, ByteBuffer.class, value -> OpaqueDataValue.builder().value(value).build());
    }
}