package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.ServerPort;

import java.io.IOException;

public class ServerPortTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ServerPort, ServerPort.ServerPortBuilder> {

    public ServerPortTtlvDeserializer() {
        super(ServerPort.kmipTag, ServerPort.encodingType);
    }

    @Override
    protected ServerPort.ServerPortBuilder createBuilder() {
        return ServerPort.builder();
    }

    @Override
    protected void setValue(ServerPort.ServerPortBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected ServerPort build(ServerPort.ServerPortBuilder builder) {
        return builder.build();
    }
}