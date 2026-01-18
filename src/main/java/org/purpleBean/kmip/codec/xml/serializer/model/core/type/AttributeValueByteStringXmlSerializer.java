package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringXmlSerializer() {
        super(AttributeValueByteString::getValue);
    }
}