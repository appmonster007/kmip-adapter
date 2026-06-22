package org.purpleBean.kmip.codec.json.model.v3_0.structure;

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
import org.purpleBean.kmip.model.v3_0.structure.PasswordCredential;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PasswordCredential Json Serialization Tests")
class PasswordCredentialJsonTest extends AbstractJsonSerializationTestSuite<PasswordCredential> {

    @Override
    public Class<PasswordCredential> type() {
        return PasswordCredential.class;
    }

    @Override
    public PasswordCredential createDefault() {
        return PasswordCredential.builder()
                .password(Password.of("s3cr3t"))
                .build();
    }

    @Override
    public PasswordCredential createVariant() {
        return PasswordCredential.builder()
                .password(Password.of("passw0rd"))
                .iterationCount(IterationCount.of(1000))
                .build();
    }
}