package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueXmlDeserializer extends AbstractKmipXmlDeserializer<DigestValue, ByteBuffer> {

    public DigestValueXmlDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType, ByteBuffer.class, value -> DigestValue.builder().value(value).build());
    }
}