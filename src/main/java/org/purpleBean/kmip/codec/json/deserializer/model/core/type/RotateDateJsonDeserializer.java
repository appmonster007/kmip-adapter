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
import org.purpleBean.kmip.model.core.type.RotateDate;

import java.io.IOException;

public class RotateDateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RotateDate, RotateDate.RotateDateBuilder> {

    public RotateDateJsonDeserializer() {
        super(RotateDate.kmipTag, RotateDate.encodingType);
    }

    @Override
    protected RotateDate.RotateDateBuilder createBuilder() {
        return RotateDate.builder();
    }

    @Override
    protected void setValue(RotateDate.RotateDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected RotateDate build(RotateDate.RotateDateBuilder builder) {
        return builder.build();
    }
}