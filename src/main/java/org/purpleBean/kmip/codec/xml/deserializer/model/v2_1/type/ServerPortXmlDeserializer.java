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
import org.purpleBean.kmip.model.v2_1.type.ServerPort;

import java.io.IOException;

public class ServerPortXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ServerPort, ServerPort.ServerPortBuilder> {

    public ServerPortXmlDeserializer() {
        super(ServerPort.kmipTag, ServerPort.encodingType);
    }

    @Override
    protected ServerPort.ServerPortBuilder createBuilder() {
        return ServerPort.builder();
    }

    @Override
    protected void setValue(ServerPort.ServerPortBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ServerPort build(ServerPort.ServerPortBuilder builder) {
        return builder.build();
    }
}