package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LoginOpRequestPayload Ttlv Serialization Tests")
class LoginOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<LoginOpRequestPayload> {

    @Override
    public Class<LoginOpRequestPayload> type() {
        return LoginOpRequestPayload.class;
    }

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public LoginOpRequestPayload createDefault() {
        return LoginOpRequestPayload.builder()
                .leaseTime(LeaseTime.of(3600))
                .requestCount(RequestCount.of(10))
                .build();
    }

    @Override
    public LoginOpRequestPayload createVariant() {
        return LoginOpRequestPayload.builder()
                .leaseTime(LeaseTime.of(7200))
                .requestCount(RequestCount.of(20))
                .build();
    }
}