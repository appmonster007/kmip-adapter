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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.DelegatedLoginOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DelegatedLoginOpResponsePayload Ttlv Serialization Tests")
class DelegatedLoginOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DelegatedLoginOpResponsePayload> {

    @Override
    public Class<DelegatedLoginOpResponsePayload> type() {
        return DelegatedLoginOpResponsePayload.class;
    }

    @Override
    public DelegatedLoginOpResponsePayload createDefault() {
        return DelegatedLoginOpResponsePayload.builder().build();
    }

    @Override
    public DelegatedLoginOpResponsePayload createVariant() {
        return DelegatedLoginOpResponsePayload.builder().build();
    }
}