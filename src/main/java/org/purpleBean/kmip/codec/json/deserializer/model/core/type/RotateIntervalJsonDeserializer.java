package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

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
import org.purpleBean.kmip.model.core.type.RotateInterval;

import java.io.IOException;

public class RotateIntervalJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RotateInterval, RotateInterval.RotateIntervalBuilder> {

    public RotateIntervalJsonDeserializer() {
        super(RotateInterval.kmipTag, RotateInterval.encodingType);
    }

    @Override
    protected RotateInterval.RotateIntervalBuilder createBuilder() {
        return RotateInterval.builder();
    }

    @Override
    protected void setValue(RotateInterval.RotateIntervalBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected RotateInterval build(RotateInterval.RotateIntervalBuilder builder) {
        return builder.build();
    }
}