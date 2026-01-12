package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringXmlSerializer() {
        super(AttributeValueByteString::getValue);
    }
}