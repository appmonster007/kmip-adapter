package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlDeserializer extends AbstractKmipXmlDeserializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringXmlDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType, ByteBuffer.class, value -> AttributeValueByteString.builder().value(value).build());
    }
}