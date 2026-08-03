package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

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
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerHashedPassword Ttlv Serialization Tests")
class ServerHashedPasswordTtlvTest extends AbstractTtlvSerializationTestSuite<ServerHashedPassword> {

    @Override
    public Class<ServerHashedPassword> type() {
        return ServerHashedPassword.class;
    }

    @Override
    public ServerHashedPassword createDefault() {
        return ServerHashedPassword.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public ServerHashedPassword createVariant() {
        return ServerHashedPassword.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}