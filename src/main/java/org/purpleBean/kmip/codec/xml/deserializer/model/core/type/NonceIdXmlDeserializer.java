package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NonceId, ByteBuffer> {

    public NonceIdXmlDeserializer() {
        super(NonceId.kmipTag, NonceId.encodingType, ByteBuffer.class, value -> NonceId.builder().value(value).build());
    }
}