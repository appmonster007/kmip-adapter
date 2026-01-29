package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueInteger, AttributeValueInteger.AttributeValueIntegerBuilder> {

    public AttributeValueIntegerTtlvDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType);
    }

    @Override
    protected AttributeValueInteger.AttributeValueIntegerBuilder createBuilder() {
        return AttributeValueInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueInteger.AttributeValueIntegerBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected AttributeValueInteger build(AttributeValueInteger.AttributeValueIntegerBuilder builder) {
        return builder.build();
    }
}
