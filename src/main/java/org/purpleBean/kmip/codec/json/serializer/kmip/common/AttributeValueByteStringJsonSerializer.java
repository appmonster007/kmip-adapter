package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringJsonSerializer() {
        super(AttributeValueByteString::getValue);
    }
}