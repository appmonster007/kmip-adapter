package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v3_0.structure.HashedPasswordCredential;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("HashedPasswordCredential Ttlv Serialization Tests")
class HashedPasswordCredentialTtlvTest extends AbstractTtlvSerializationTestSuite<HashedPasswordCredential> {

    @Override
    public Class<HashedPasswordCredential> type() {
        return HashedPasswordCredential.class;
    }

    @Override
    public HashedPasswordCredential createDefault() {
        return HashedPasswordCredential.builder()
                .hashedUsernamePassword(HashedUsernamePassword.of(new byte[]{0x01, 0x02, 0x03}))
                .hashedPasswordUsername(HashedPasswordUsername.of(new byte[]{0x04, 0x05, 0x06}))
                .build();
    }

    @Override
    public HashedPasswordCredential createVariant() {
        return HashedPasswordCredential.builder()
                .hashedUsernamePassword(HashedUsernamePassword.of(new byte[]{0x07, 0x08, 0x09}))
                .hashedPasswordUsername(HashedPasswordUsername.of(new byte[]{0x0A, 0x0B, 0x0C}))
                .build();
    }
}