package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltTtlvSerializer extends AbstractKmipTtlvSerializer<Salt, ByteBuffer> {

    public SaltTtlvSerializer() {
        super(Salt::getValue);
    }
}