package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.OtpCounter;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OtpCounterTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpCounter, OtpCounter.OtpCounterBuilder> {

    public OtpCounterTtlvDeserializer() {
        super(OtpCounter.kmipTag, OtpCounter.encodingType);
    }

    @Override
    protected OtpCounter.OtpCounterBuilder createBuilder() {
        return OtpCounter.builder();
    }

    @Override
    protected void setValue(OtpCounter.OtpCounterBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected OtpCounter build(OtpCounter.OtpCounterBuilder builder) {
        return builder.build();
    }
}
