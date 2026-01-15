package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.nio.ByteBuffer;

public class SaltJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Salt, ByteBuffer> {

    public SaltJsonSerializer() {
        super(Salt::getValue);
    }
}