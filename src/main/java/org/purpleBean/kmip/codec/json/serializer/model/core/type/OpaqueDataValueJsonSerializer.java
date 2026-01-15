package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueJsonSerializer() {
        super(OpaqueDataValue::getValue);
    }
}