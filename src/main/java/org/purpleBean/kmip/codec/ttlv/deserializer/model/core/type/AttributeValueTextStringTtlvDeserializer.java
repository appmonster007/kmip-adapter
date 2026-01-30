package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueTextStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueTextString, AttributeValueTextString.AttributeValueTextStringBuilder> {

    public AttributeValueTextStringTtlvDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType);
    }

    @Override
    protected AttributeValueTextString.AttributeValueTextStringBuilder createBuilder() {
        return AttributeValueTextString.builder();
    }

    @Override
    protected void setValue(AttributeValueTextString.AttributeValueTextStringBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected AttributeValueTextString build(AttributeValueTextString.AttributeValueTextStringBuilder builder) {
        return builder.build();
    }
}
