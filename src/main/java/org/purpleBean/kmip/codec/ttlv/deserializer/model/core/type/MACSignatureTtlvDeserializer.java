package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MACSignatureTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MACSignature, MACSignature.MACSignatureBuilder> {

    public MACSignatureTtlvDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType);
    }

    @Override
    protected MACSignature.MACSignatureBuilder createBuilder() {
        return MACSignature.builder();
    }

    @Override
    protected void setValue(MACSignature.MACSignatureBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected MACSignature build(MACSignature.MACSignatureBuilder builder) {
        return builder.build();
    }
}
