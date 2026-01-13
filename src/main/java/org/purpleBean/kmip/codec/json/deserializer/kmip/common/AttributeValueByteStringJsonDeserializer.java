package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringJsonDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType, ByteBuffer.class, value -> AttributeValueByteString.builder().value(value).build());
    }
}