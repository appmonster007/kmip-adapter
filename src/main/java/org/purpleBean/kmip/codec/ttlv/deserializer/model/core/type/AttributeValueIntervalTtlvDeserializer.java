package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueIntervalTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueInterval, AttributeValueInterval.AttributeValueIntervalBuilder> {

    public AttributeValueIntervalTtlvDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType);
    }

    @Override
    protected AttributeValueInterval.AttributeValueIntervalBuilder createBuilder() {
        return AttributeValueInterval.builder();
    }

    @Override
    protected void setValue(AttributeValueInterval.AttributeValueIntervalBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected AttributeValueInterval build(AttributeValueInterval.AttributeValueIntervalBuilder builder) {
        return builder.build();
    }
}
