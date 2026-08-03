package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;

import java.io.IOException;

public class ServerHashedPasswordTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ServerHashedPassword, ServerHashedPassword.ServerHashedPasswordBuilder> {

    public ServerHashedPasswordTtlvDeserializer() {
        super(ServerHashedPassword.kmipTag, ServerHashedPassword.encodingType);
    }

    @Override
    protected ServerHashedPassword.ServerHashedPasswordBuilder createBuilder() {
        return ServerHashedPassword.builder();
    }

    @Override
    protected void setValue(ServerHashedPassword.ServerHashedPasswordBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected ServerHashedPassword build(ServerHashedPassword.ServerHashedPasswordBuilder builder) {
        return builder.build();
    }
}