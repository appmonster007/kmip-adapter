package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DigestValue, ByteBuffer> {

    public DigestValueTtlvSerializer() {
        super(DigestValue::getValue);
    }
}