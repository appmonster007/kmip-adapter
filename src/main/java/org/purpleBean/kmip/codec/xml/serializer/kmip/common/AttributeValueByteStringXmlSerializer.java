package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringXmlSerializer() {
        super(AttributeValueByteString::getValue);
    }
}