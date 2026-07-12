package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

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
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.RotateGeneration;

import java.io.IOException;

public class RotateGenerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateGeneration, RotateGeneration.RotateGenerationBuilder> {

    public RotateGenerationXmlDeserializer() {
        super(RotateGeneration.kmipTag, RotateGeneration.encodingType);
    }

    @Override
    protected RotateGeneration.RotateGenerationBuilder createBuilder() {
        return RotateGeneration.builder();
    }

    @Override
    protected void setValue(RotateGeneration.RotateGenerationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected RotateGeneration build(RotateGeneration.RotateGenerationBuilder builder) {
        return builder.build();
    }
}