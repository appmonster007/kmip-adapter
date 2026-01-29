package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueByteString, AttributeValueByteString.AttributeValueByteStringBuilder> {

    public AttributeValueByteStringTtlvDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType);
    }

    @Override
    protected AttributeValueByteString.AttributeValueByteStringBuilder createBuilder() {
        return AttributeValueByteString.builder();
    }

    @Override
    protected void setValue(AttributeValueByteString.AttributeValueByteStringBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected AttributeValueByteString build(AttributeValueByteString.AttributeValueByteStringBuilder builder) {
        return builder.build();
    }
}