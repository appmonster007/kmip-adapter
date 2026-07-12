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
import org.purpleBean.kmip.model.v2_1.type.ClientCorrelationValue;

import java.io.IOException;

public class ClientCorrelationValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ClientCorrelationValue, ClientCorrelationValue.ClientCorrelationValueBuilder> {

    public ClientCorrelationValueXmlDeserializer() {
        super(ClientCorrelationValue.kmipTag, ClientCorrelationValue.encodingType);
    }

    @Override
    protected ClientCorrelationValue.ClientCorrelationValueBuilder createBuilder() {
        return ClientCorrelationValue.builder();
    }

    @Override
    protected void setValue(ClientCorrelationValue.ClientCorrelationValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ClientCorrelationValue build(ClientCorrelationValue.ClientCorrelationValueBuilder builder) {
        return builder.build();
    }
}