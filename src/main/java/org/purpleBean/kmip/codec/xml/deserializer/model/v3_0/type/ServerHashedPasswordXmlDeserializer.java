package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;

import java.io.IOException;

public class ServerHashedPasswordXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ServerHashedPassword, ServerHashedPassword.ServerHashedPasswordBuilder> {

    public ServerHashedPasswordXmlDeserializer() {
        super(ServerHashedPassword.kmipTag, ServerHashedPassword.encodingType);
    }

    @Override
    protected ServerHashedPassword.ServerHashedPasswordBuilder createBuilder() {
        return ServerHashedPassword.builder();
    }

    @Override
    protected void setValue(ServerHashedPassword.ServerHashedPasswordBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected ServerHashedPassword build(ServerHashedPassword.ServerHashedPasswordBuilder builder) {
        return builder.build();
    }
}