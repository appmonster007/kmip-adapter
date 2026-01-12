package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringTtlvSerializer() {
        super(AttributeValueByteString::getValue);
    }
}