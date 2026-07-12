package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.PasswordCredential;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v3_0.type.PasswordSalt;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;

public class PasswordCredentialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PasswordCredential, PasswordCredential.PasswordCredentialBuilder> {

    public PasswordCredentialTtlvDeserializer() {
        super(PasswordCredential.kmipTag, PasswordCredential.encodingType);
    }

    @Override
    protected PasswordCredential.PasswordCredentialBuilder createBuilder() {
        return PasswordCredential.builder();
    }

    @Override
    protected void setValue(PasswordCredential.PasswordCredentialBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag == Password.kmipTag.getValue()) {
            builder.password(mapper.readValue(p, Password.class));
        } else if (nodeTag == PasswordSalt.kmipTag.getValue()) {
            builder.passwordSalt(mapper.readValue(p, PasswordSalt.class));
        } else if (nodeTag == PasswordSaltAlgorithm.kmipTag.getValue()) {
            builder.passwordSaltAlgorithm(mapper.readValue(p, PasswordSaltAlgorithm.class));
        } else if (nodeTag == SaltedPassword.kmipTag.getValue()) {
            builder.saltedPassword(mapper.readValue(p, SaltedPassword.class));
        } else if (nodeTag == IterationCount.kmipTag.getValue()) {
            builder.iterationCount(mapper.readValue(p, IterationCount.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PasswordCredential build(PasswordCredential.PasswordCredentialBuilder builder) {
        return builder.build();
    }
}