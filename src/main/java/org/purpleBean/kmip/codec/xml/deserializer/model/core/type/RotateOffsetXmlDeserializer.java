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
import org.purpleBean.kmip.model.core.type.RotateOffset;

import java.io.IOException;

public class RotateOffsetXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RotateOffset, RotateOffset.RotateOffsetBuilder> {

    public RotateOffsetXmlDeserializer() {
        super(RotateOffset.kmipTag, RotateOffset.encodingType);
    }

    @Override
    protected RotateOffset.RotateOffsetBuilder createBuilder() {
        return RotateOffset.builder();
    }

    @Override
    protected void setValue(RotateOffset.RotateOffsetBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected RotateOffset build(RotateOffset.RotateOffsetBuilder builder) {
        return builder.build();
    }
}