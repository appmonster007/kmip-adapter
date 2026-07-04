package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.OtpInterval;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OtpIntervalTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpInterval, OtpInterval.OtpIntervalBuilder> {

    public OtpIntervalTtlvDeserializer() {
        super(OtpInterval.kmipTag, OtpInterval.encodingType);
    }

    @Override
    protected OtpInterval.OtpIntervalBuilder createBuilder() {
        return OtpInterval.builder();
    }

    @Override
    protected void setValue(OtpInterval.OtpIntervalBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected OtpInterval build(OtpInterval.OtpIntervalBuilder builder) {
        return builder.build();
    }
}
