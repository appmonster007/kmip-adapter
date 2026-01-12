package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringTtlvDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType, ByteBuffer.class, value -> AttributeValueByteString.builder().value(value).build());
    }
}