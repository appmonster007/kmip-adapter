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
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;

import java.io.IOException;

public class ServerCorrelationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ServerCorrelationValue, ServerCorrelationValue.ServerCorrelationValueBuilder> {

    public ServerCorrelationValueXmlDeserializer() {
        super(ServerCorrelationValue.kmipTag, ServerCorrelationValue.encodingType);
    }

    @Override
    protected ServerCorrelationValue.ServerCorrelationValueBuilder createBuilder() {
        return ServerCorrelationValue.builder();
    }

    @Override
    protected void setValue(ServerCorrelationValue.ServerCorrelationValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ServerCorrelationValue build(ServerCorrelationValue.ServerCorrelationValueBuilder builder) {
        return builder.build();
    }
}