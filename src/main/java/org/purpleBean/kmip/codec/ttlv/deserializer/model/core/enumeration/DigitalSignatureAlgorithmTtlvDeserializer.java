package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DigitalSignatureAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DigitalSignatureAlgorithm, DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder> {

    public DigitalSignatureAlgorithmTtlvDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType);
    }

    @Override
    protected DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder createBuilder() {
        return DigitalSignatureAlgorithm.builder();
    }

    @Override
    protected void setValue(DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(DigitalSignatureAlgorithm.fromValue(value));
    }

    @Override
    protected DigitalSignatureAlgorithm build(DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder) {
        return builder.build();
    }
}
