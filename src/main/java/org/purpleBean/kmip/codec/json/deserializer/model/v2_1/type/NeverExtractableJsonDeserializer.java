package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;

import java.io.IOException;

public class NeverExtractableJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NeverExtractable, NeverExtractable.NeverExtractableBuilder> {

    public NeverExtractableJsonDeserializer() {
        super(NeverExtractable.kmipTag, NeverExtractable.encodingType);
    }

    @Override
    protected NeverExtractable.NeverExtractableBuilder createBuilder() {
        return NeverExtractable.builder();
    }

    @Override
    protected void setValue(NeverExtractable.NeverExtractableBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected NeverExtractable build(NeverExtractable.NeverExtractableBuilder builder) {
        return builder.build();
    }
}