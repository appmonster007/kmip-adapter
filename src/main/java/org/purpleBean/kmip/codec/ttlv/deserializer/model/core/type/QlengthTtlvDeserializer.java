package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Qlength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class QlengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Qlength, Qlength.QlengthBuilder> {

    public QlengthTtlvDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType);
    }

    @Override
    protected Qlength.QlengthBuilder createBuilder() {
        return Qlength.builder();
    }

    @Override
    protected void setValue(Qlength.QlengthBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected Qlength build(Qlength.QlengthBuilder builder) {
        return builder.build();
    }
}
