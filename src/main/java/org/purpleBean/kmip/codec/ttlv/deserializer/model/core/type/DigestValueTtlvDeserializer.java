package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DigestValue, ByteBuffer> {

    public DigestValueTtlvDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType, ByteBuffer.class, value -> DigestValue.builder().value(value).build());
    }
}