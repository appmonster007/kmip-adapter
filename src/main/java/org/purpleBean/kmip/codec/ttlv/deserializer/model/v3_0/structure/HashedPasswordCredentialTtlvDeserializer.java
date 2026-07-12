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
import org.purpleBean.kmip.model.v3_0.structure.HashedPasswordCredential;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.model.v3_0.type.HashedPasswordUsername;
import org.purpleBean.kmip.model.v3_0.type.HashedUsernamePassword;

public class HashedPasswordCredentialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<HashedPasswordCredential, HashedPasswordCredential.HashedPasswordCredentialBuilder> {

    public HashedPasswordCredentialTtlvDeserializer() {
        super(HashedPasswordCredential.kmipTag, HashedPasswordCredential.encodingType);
    }

    @Override
    protected HashedPasswordCredential.HashedPasswordCredentialBuilder createBuilder() {
        return HashedPasswordCredential.builder();
    }

    @Override
    protected void setValue(HashedPasswordCredential.HashedPasswordCredentialBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM -> builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
            case KmipTag.Standard.HASHED_USERNAME_PASSWORD -> builder.hashedUsernamePassword(mapper.readValue(p, HashedUsernamePassword.class));
            case KmipTag.Standard.HASHED_PASSWORD_USERNAME -> builder.hashedPasswordUsername(mapper.readValue(p, HashedPasswordUsername.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashedPasswordCredential build(HashedPasswordCredential.HashedPasswordCredentialBuilder builder) {
        return builder.build();
    }
}