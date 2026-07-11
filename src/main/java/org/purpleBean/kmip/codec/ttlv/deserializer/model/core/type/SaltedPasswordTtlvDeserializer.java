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
import org.purpleBean.kmip.model.core.type.SaltedPassword;

import java.io.IOException;

public class SaltedPasswordTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SaltedPassword, SaltedPassword.SaltedPasswordBuilder> {

    public SaltedPasswordTtlvDeserializer() {
        super(SaltedPassword.kmipTag, SaltedPassword.encodingType);
    }

    @Override
    protected SaltedPassword.SaltedPasswordBuilder createBuilder() {
        return SaltedPassword.builder();
    }

    @Override
    protected void setValue(SaltedPassword.SaltedPasswordBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected SaltedPassword build(SaltedPassword.SaltedPasswordBuilder builder) {
        return builder.build();
    }
}