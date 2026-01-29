package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Fresh;

import java.io.IOException;
import java.nio.ByteBuffer;

public class FreshTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Fresh, Fresh.FreshBuilder> {

    public FreshTtlvDeserializer() {
        super(Fresh.kmipTag, Fresh.encodingType);
    }

    @Override
    protected Fresh.FreshBuilder createBuilder() {
        return Fresh.builder();
    }

    @Override
    protected void setValue(Fresh.FreshBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Boolean.class));
    }

    @Override
    protected Fresh build(Fresh.FreshBuilder builder) {
        return builder.build();
    }
}
