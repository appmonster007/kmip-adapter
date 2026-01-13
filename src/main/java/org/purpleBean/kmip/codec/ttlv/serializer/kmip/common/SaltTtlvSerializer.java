package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Salt, ByteBuffer> {

    public SaltTtlvSerializer() {
        super(Salt::getValue);
    }
}