package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.RandomNumberGenerator;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

public class RandomNumberGeneratorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RandomNumberGenerator, RandomNumberGenerator.RandomNumberGeneratorBuilder> {

    public RandomNumberGeneratorTtlvDeserializer() {
        super(RandomNumberGenerator.kmipTag, RandomNumberGenerator.encodingType);
    }

    @Override
    protected RandomNumberGenerator.RandomNumberGeneratorBuilder createBuilder() {
        return RandomNumberGenerator.builder();
    }

    @Override
    protected void setValue(RandomNumberGenerator.RandomNumberGeneratorBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.RNG_PARAMETERS -> builder.rngParameters(mapper.readValue(p, RngParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RandomNumberGenerator build(RandomNumberGenerator.RandomNumberGeneratorBuilder builder) {
        return builder.build();
    }
}