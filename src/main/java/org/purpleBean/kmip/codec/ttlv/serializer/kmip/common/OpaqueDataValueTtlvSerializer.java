package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueTtlvSerializer() {
        super(OpaqueDataValue::getValue);
    }
}