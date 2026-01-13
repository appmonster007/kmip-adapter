package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DigestValue, ByteBuffer> {

    public DigestValueTtlvSerializer() {
        super(DigestValue::getValue);
    }
}