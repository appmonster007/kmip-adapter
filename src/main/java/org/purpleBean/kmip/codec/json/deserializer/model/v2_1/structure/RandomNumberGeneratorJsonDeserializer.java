package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.RandomNumberGenerator;

import java.io.IOException;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;

public class RandomNumberGeneratorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RandomNumberGenerator, RandomNumberGenerator.RandomNumberGeneratorBuilder> {

    public RandomNumberGeneratorJsonDeserializer() {
        super(RandomNumberGenerator.kmipTag, RandomNumberGenerator.encodingType);
    }

    @Override
    protected RandomNumberGenerator.RandomNumberGeneratorBuilder createBuilder() {
        return RandomNumberGenerator.builder();
    }

    @Override
    protected void setValue(RandomNumberGenerator.RandomNumberGeneratorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.RNG_PARAMETERS -> builder.rngParameters(ctxt.readValue(p, RngParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RandomNumberGenerator build(RandomNumberGenerator.RandomNumberGeneratorBuilder builder) {
        return builder.build();
    }
}