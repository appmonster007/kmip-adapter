package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Salt;

import java.nio.ByteBuffer;

public class SaltTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Salt, ByteBuffer> {

    public SaltTtlvSerializer() {
        super(Salt::getValue);
    }
}