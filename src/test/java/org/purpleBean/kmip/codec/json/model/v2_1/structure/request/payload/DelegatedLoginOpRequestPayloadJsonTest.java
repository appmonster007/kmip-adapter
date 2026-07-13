package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DelegatedLoginOpRequestPayload Json Serialization Tests")
class DelegatedLoginOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<DelegatedLoginOpRequestPayload> {

    @Override
    public Class<DelegatedLoginOpRequestPayload> type() {
        return DelegatedLoginOpRequestPayload.class;
    }
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public DelegatedLoginOpRequestPayload createDefault() {
        return DelegatedLoginOpRequestPayload.builder()
                .leaseTime(LeaseTime.of(3600))
                .requestCount(RequestCount.of(10))
                .build();
    }

    @Override
    public DelegatedLoginOpRequestPayload createVariant() {
        return DelegatedLoginOpRequestPayload.builder()
                .leaseTime(LeaseTime.of(7200))
                .requestCount(RequestCount.of(20))
                .build();
    }
}