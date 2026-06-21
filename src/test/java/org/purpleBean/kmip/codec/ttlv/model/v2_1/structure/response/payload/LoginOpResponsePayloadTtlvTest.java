package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LoginOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LoginOpResponsePayload Ttlv Serialization Tests")
class LoginOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<LoginOpResponsePayload> {

    @Override
    public Class<LoginOpResponsePayload> type() {
        return LoginOpResponsePayload.class;
    }

    @Override
    public LoginOpResponsePayload createDefault() {
        return LoginOpResponsePayload.builder().build();
    }

    @Override
    public LoginOpResponsePayload createVariant() {
        return LoginOpResponsePayload.builder().build();
    }
}