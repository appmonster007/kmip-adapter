package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringXmlDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType, ByteBuffer.class, value -> AttributeValueByteString.builder().value(value).build());
    }
}