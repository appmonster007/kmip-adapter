package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

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
import org.purpleBean.kmip.model.core.type.PasswordSalt;

import java.io.IOException;

public class PasswordSaltTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PasswordSalt, PasswordSalt.PasswordSaltBuilder> {

    public PasswordSaltTtlvDeserializer() {
        super(PasswordSalt.kmipTag, PasswordSalt.encodingType);
    }

    @Override
    protected PasswordSalt.PasswordSaltBuilder createBuilder() {
        return PasswordSalt.builder();
    }

    @Override
    protected void setValue(PasswordSalt.PasswordSaltBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected PasswordSalt build(PasswordSalt.PasswordSaltBuilder builder) {
        return builder.build();
    }
}