package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueJsonSerializer extends AbstractKmipJsonSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueJsonSerializer() {
        super(OpaqueDataValue::getValue);
    }
}