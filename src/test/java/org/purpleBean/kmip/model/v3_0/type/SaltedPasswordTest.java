package org.purpleBean.kmip.model.v3_0.type;

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

import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.model.v3_0.type.SaltedPassword;

@DisplayName("SaltedPassword Domain Tests")
class SaltedPasswordTest extends AbstractKmipDataTypeTestSuite<SaltedPassword> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<SaltedPassword> type() {
        return SaltedPassword.class;
    }

    @Override
    public SaltedPassword createDefault() {
        return SaltedPassword.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}