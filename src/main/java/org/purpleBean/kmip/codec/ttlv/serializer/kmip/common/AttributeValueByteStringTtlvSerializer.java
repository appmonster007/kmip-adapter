package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringTtlvSerializer() {
        super(AttributeValueByteString::getValue);
    }
}