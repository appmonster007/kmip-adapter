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
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.io.IOException;

public class CorrelationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CorrelationValue, CorrelationValue.CorrelationValueBuilder> {

    public CorrelationValueXmlDeserializer() {
        super(CorrelationValue.kmipTag, CorrelationValue.encodingType);
    }

    @Override
    protected CorrelationValue.CorrelationValueBuilder createBuilder() {
        return CorrelationValue.builder();
    }

    @Override
    protected void setValue(CorrelationValue.CorrelationValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected CorrelationValue build(CorrelationValue.CorrelationValueBuilder builder) {
        return builder.build();
    }
}