package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DigestValue, ByteBuffer> {

    public DigestValueXmlDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType, ByteBuffer.class, value -> DigestValue.builder().value(value).build());
    }
}