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
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SaltedPassword Ttlv Serialization Tests")
class SaltedPasswordTtlvTest extends AbstractTtlvSerializationTestSuite<SaltedPassword> {

    @Override
    public Class<SaltedPassword> type() {
        return SaltedPassword.class;
    }

    @Override
    public SaltedPassword createDefault() {
        return SaltedPassword.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public SaltedPassword createVariant() {
        return SaltedPassword.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}