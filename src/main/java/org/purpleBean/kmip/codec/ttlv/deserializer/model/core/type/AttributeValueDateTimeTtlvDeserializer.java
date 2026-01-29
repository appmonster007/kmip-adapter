package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;

public class AttributeValueDateTimeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueDateTime, AttributeValueDateTime.AttributeValueDateTimeBuilder> {

    public AttributeValueDateTimeTtlvDeserializer() {
        super(AttributeValueDateTime.kmipTag, AttributeValueDateTime.encodingType);
    }

    @Override
    protected AttributeValueDateTime.AttributeValueDateTimeBuilder createBuilder() {
        return AttributeValueDateTime.builder();
    }

    @Override
    protected void setValue(AttributeValueDateTime.AttributeValueDateTimeBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, OffsetDateTime.class));
    }

    @Override
    protected AttributeValueDateTime build(AttributeValueDateTime.AttributeValueDateTimeBuilder builder) {
        return builder.build();
    }
}