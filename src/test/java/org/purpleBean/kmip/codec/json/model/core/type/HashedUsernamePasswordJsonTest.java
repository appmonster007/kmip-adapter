package org.purpleBean.kmip.codec.json.model.core.type;

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
import org.purpleBean.kmip.model.core.type.HashedUsernamePassword;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashedUsernamePassword Json Serialization Tests")
class HashedUsernamePasswordJsonTest extends AbstractJsonSerializationTestSuite<HashedUsernamePassword> {

    @Override
    public Class<HashedUsernamePassword> type() {
        return HashedUsernamePassword.class;
    }

    @Override
    public HashedUsernamePassword createDefault() {
        return HashedUsernamePassword.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public HashedUsernamePassword createVariant() {
        return HashedUsernamePassword.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}