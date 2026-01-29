package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueBooleanTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueBoolean, AttributeValueBoolean.AttributeValueBooleanBuilder> {

    public AttributeValueBooleanTtlvDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType);
    }

    @Override
    protected AttributeValueBoolean.AttributeValueBooleanBuilder createBuilder() {
        return AttributeValueBoolean.builder();
    }

    @Override
    protected void setValue(AttributeValueBoolean.AttributeValueBooleanBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected AttributeValueBoolean build(AttributeValueBoolean.AttributeValueBooleanBuilder builder) {
        return builder.build();
    }
}