package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DataByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DataByteString, DataByteString.DataByteStringBuilder> {

    public DataByteStringTtlvDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType);
    }

    @Override
    protected DataByteString.DataByteStringBuilder createBuilder() {
        return DataByteString.builder();
    }

    @Override
    protected void setValue(DataByteString.DataByteStringBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected DataByteString build(DataByteString.DataByteStringBuilder builder) {
        return builder.build();
    }
}
