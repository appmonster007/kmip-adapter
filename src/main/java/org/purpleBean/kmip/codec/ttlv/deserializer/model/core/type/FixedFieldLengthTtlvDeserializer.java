package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class FixedFieldLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<FixedFieldLength, FixedFieldLength.FixedFieldLengthBuilder> {

    public FixedFieldLengthTtlvDeserializer() {
        super(FixedFieldLength.kmipTag, FixedFieldLength.encodingType);
    }

    @Override
    protected FixedFieldLength.FixedFieldLengthBuilder createBuilder() {
        return FixedFieldLength.builder();
    }

    @Override
    protected void setValue(FixedFieldLength.FixedFieldLengthBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected FixedFieldLength build(FixedFieldLength.FixedFieldLengthBuilder builder) {
        return builder.build();
    }
}
