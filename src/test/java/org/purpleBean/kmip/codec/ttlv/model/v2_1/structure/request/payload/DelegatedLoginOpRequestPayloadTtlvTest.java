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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DelegatedLoginOpRequestPayload Ttlv Serialization Tests")
class DelegatedLoginOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DelegatedLoginOpRequestPayload> {

    @Override
    public Class<DelegatedLoginOpRequestPayload> type() {
        return DelegatedLoginOpRequestPayload.class;
    }

    @Override
    public DelegatedLoginOpRequestPayload createDefault() {
        return DelegatedLoginOpRequestPayload.builder().build();
    }

    @Override
    public DelegatedLoginOpRequestPayload createVariant() {
        return DelegatedLoginOpRequestPayload.builder().build();
    }
}