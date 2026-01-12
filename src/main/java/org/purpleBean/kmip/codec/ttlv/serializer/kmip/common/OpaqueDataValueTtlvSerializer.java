package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvSerializer extends AbstractKmipTtlvSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueTtlvSerializer() {
        super(OpaqueDataValue::getValue);
    }
}