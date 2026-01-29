package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DigestValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DigestValue, DigestValue.DigestValueBuilder> {

    public DigestValueTtlvDeserializer() {
        super(DigestValue.kmipTag, DigestValue.encodingType);
    }

    @Override
    protected DigestValue.DigestValueBuilder createBuilder() {
        return DigestValue.builder();
    }

    @Override
    protected void setValue(DigestValue.DigestValueBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected DigestValue build(DigestValue.DigestValueBuilder builder) {
        return builder.build();
    }
}
