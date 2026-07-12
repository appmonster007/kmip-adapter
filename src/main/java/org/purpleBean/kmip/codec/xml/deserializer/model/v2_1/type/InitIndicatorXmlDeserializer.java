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
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.io.IOException;

public class InitIndicatorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitIndicator, InitIndicator.InitIndicatorBuilder> {

    public InitIndicatorXmlDeserializer() {
        super(InitIndicator.kmipTag, InitIndicator.encodingType);
    }

    @Override
    protected InitIndicator.InitIndicatorBuilder createBuilder() {
        return InitIndicator.builder();
    }

    @Override
    protected void setValue(InitIndicator.InitIndicatorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Boolean.class));
    }

    @Override
    protected InitIndicator build(InitIndicator.InitIndicatorBuilder builder) {
        return builder.build();
    }
}