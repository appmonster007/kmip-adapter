package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CryptographicLengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicLength, CryptographicLength.CryptographicLengthBuilder> {

    public CryptographicLengthTtlvDeserializer() {
        super(CryptographicLength.kmipTag, CryptographicLength.encodingType);
    }

    @Override
    protected CryptographicLength.CryptographicLengthBuilder createBuilder() {
        return CryptographicLength.builder();
    }

    @Override
    protected void setValue(CryptographicLength.CryptographicLengthBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected CryptographicLength build(CryptographicLength.CryptographicLengthBuilder builder) {
        return builder.build();
    }
}
