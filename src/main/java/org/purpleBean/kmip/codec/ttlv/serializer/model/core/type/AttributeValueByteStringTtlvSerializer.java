package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringTtlvSerializer() {
        super(AttributeValueByteString::getValue);
    }
}