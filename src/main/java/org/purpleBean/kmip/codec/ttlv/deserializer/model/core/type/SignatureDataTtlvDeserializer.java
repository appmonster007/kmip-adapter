package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.SignatureData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SignatureDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SignatureData, SignatureData.SignatureDataBuilder> {

    public SignatureDataTtlvDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType);
    }

    @Override
    protected SignatureData.SignatureDataBuilder createBuilder() {
        return SignatureData.builder();
    }

    @Override
    protected void setValue(SignatureData.SignatureDataBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected SignatureData build(SignatureData.SignatureDataBuilder builder) {
        return builder.build();
    }
}
