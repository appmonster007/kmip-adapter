package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueLongIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueLongInteger, AttributeValueLongInteger.AttributeValueLongIntegerBuilder> {

    public AttributeValueLongIntegerTtlvDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType);
    }

    @Override
    protected AttributeValueLongInteger.AttributeValueLongIntegerBuilder createBuilder() {
        return AttributeValueLongInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueLongInteger.AttributeValueLongIntegerBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Long.class));
    }

    @Override
    protected AttributeValueLongInteger build(AttributeValueLongInteger.AttributeValueLongIntegerBuilder builder) {
        return builder.build();
    }
}
