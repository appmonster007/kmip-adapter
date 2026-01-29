package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashingAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashingAlgorithm, HashingAlgorithm.HashingAlgorithmBuilder> {

    public HashingAlgorithmTtlvDeserializer() {
        super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType);
    }

    @Override
    protected HashingAlgorithm.HashingAlgorithmBuilder createBuilder() {
        return HashingAlgorithm.builder();
    }

    @Override
    protected void setValue(HashingAlgorithm.HashingAlgorithmBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(HashingAlgorithm.fromValue(value));
    }

    @Override
    protected HashingAlgorithm build(HashingAlgorithm.HashingAlgorithmBuilder builder) {
        return builder.build();
    }
}
