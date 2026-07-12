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
import org.purpleBean.kmip.model.core.type.AlwaysSensitive;

import java.io.IOException;

public class AlwaysSensitiveJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AlwaysSensitive, AlwaysSensitive.AlwaysSensitiveBuilder> {

    public AlwaysSensitiveJsonDeserializer() {
        super(AlwaysSensitive.kmipTag, AlwaysSensitive.encodingType);
    }

    @Override
    protected AlwaysSensitive.AlwaysSensitiveBuilder createBuilder() {
        return AlwaysSensitive.builder();
    }

    @Override
    protected void setValue(AlwaysSensitive.AlwaysSensitiveBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected AlwaysSensitive build(AlwaysSensitive.AlwaysSensitiveBuilder builder) {
        return builder.build();
    }
}