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
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionAdditionalData;

import java.io.IOException;

public class AuthenticatedEncryptionAdditionalDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AuthenticatedEncryptionAdditionalData, AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder> {

    public AuthenticatedEncryptionAdditionalDataTtlvDeserializer() {
        super(AuthenticatedEncryptionAdditionalData.kmipTag, AuthenticatedEncryptionAdditionalData.encodingType);
    }

    @Override
    protected AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder createBuilder() {
        return AuthenticatedEncryptionAdditionalData.builder();
    }

    @Override
    protected void setValue(AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AuthenticatedEncryptionAdditionalData build(AuthenticatedEncryptionAdditionalData.AuthenticatedEncryptionAdditionalDataBuilder builder) {
        return builder.build();
    }
}