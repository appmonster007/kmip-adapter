package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.DigestedData;

import java.io.IOException;

public class DigestedDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DigestedData, DigestedData.DigestedDataBuilder> {

    public DigestedDataXmlDeserializer() {
        super(DigestedData.kmipTag, DigestedData.encodingType);
    }

    @Override
    protected DigestedData.DigestedDataBuilder createBuilder() {
        return DigestedData.builder();
    }

    @Override
    protected void setValue(DigestedData.DigestedDataBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected DigestedData build(DigestedData.DigestedDataBuilder builder) {
        return builder.build();
    }
}