package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringTtlvDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType, ByteBuffer.class, value -> AttributeValueByteString.builder().value(value).build());
    }
}