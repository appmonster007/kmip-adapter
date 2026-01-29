package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class AttributeValueBigIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueBigInteger, AttributeValueBigInteger.AttributeValueBigIntegerBuilder> {

    public AttributeValueBigIntegerTtlvDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType);
    }

    @Override
    protected AttributeValueBigInteger.AttributeValueBigIntegerBuilder createBuilder() {
        return AttributeValueBigInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueBigInteger.AttributeValueBigIntegerBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, BigInteger.class));
    }

    @Override
    protected AttributeValueBigInteger build(AttributeValueBigInteger.AttributeValueBigIntegerBuilder builder) {
        return builder.build();
    }
}