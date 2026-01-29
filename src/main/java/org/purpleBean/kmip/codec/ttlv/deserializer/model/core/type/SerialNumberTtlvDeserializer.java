package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SerialNumberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SerialNumber, SerialNumber.SerialNumberBuilder> {

    public SerialNumberTtlvDeserializer() {
        super(SerialNumber.kmipTag, SerialNumber.encodingType);
    }

    @Override
    protected SerialNumber.SerialNumberBuilder createBuilder() {
        return SerialNumber.builder();
    }

    @Override
    protected void setValue(SerialNumber.SerialNumberBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected SerialNumber build(SerialNumber.SerialNumberBuilder builder) {
        return builder.build();
    }
}
