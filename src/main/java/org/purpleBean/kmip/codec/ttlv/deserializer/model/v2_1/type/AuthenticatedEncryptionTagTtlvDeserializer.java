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
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;

import java.io.IOException;

public class AuthenticatedEncryptionTagTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AuthenticatedEncryptionTag, AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder> {

    public AuthenticatedEncryptionTagTtlvDeserializer() {
        super(AuthenticatedEncryptionTag.kmipTag, AuthenticatedEncryptionTag.encodingType);
    }

    @Override
    protected AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder createBuilder() {
        return AuthenticatedEncryptionTag.builder();
    }

    @Override
    protected void setValue(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AuthenticatedEncryptionTag build(AuthenticatedEncryptionTag.AuthenticatedEncryptionTagBuilder builder) {
        return builder.build();
    }
}