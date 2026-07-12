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
import org.purpleBean.kmip.model.v2_1.type.ServerUri;

import java.io.IOException;

public class ServerUriTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ServerUri, ServerUri.ServerUriBuilder> {

    public ServerUriTtlvDeserializer() {
        super(ServerUri.kmipTag, ServerUri.encodingType);
    }

    @Override
    protected ServerUri.ServerUriBuilder createBuilder() {
        return ServerUri.builder();
    }

    @Override
    protected void setValue(ServerUri.ServerUriBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected ServerUri build(ServerUri.ServerUriBuilder builder) {
        return builder.build();
    }
}