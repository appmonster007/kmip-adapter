package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DigestValue, ByteBuffer> {

    public DigestValueTtlvDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType, ByteBuffer.class, value -> DigestValue.builder().value(value).build());
    }
}