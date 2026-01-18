package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueTtlvSerializer() {
        super(OpaqueDataValue::getValue);
    }
}