package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.enumeration.OtpAlgorithm;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OtpAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpAlgorithm, OtpAlgorithm.OtpAlgorithmBuilder> {

    public OtpAlgorithmTtlvDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType);
    }

    @Override
    protected OtpAlgorithm.OtpAlgorithmBuilder createBuilder() {
        return OtpAlgorithm.builder();
    }

    @Override
    protected void setValue(OtpAlgorithm.OtpAlgorithmBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(OtpAlgorithm.fromValue(value));
    }

    @Override
    protected OtpAlgorithm build(OtpAlgorithm.OtpAlgorithmBuilder builder) {
        return builder.build();
    }
}
