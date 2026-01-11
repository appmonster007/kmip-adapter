package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueJsonDeserializer extends AbstractKmipJsonDeserializer<DigestValue, ByteBuffer> {

    public DigestValueJsonDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType, ByteBuffer.class, value -> DigestValue.builder().value(value).build());
    }
}