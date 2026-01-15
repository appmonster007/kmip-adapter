package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.nio.ByteBuffer;

public class AttributeValueByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueByteString, ByteBuffer> {

    public AttributeValueByteStringJsonSerializer() {
        super(AttributeValueByteString::getValue);
    }
}