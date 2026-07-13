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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DeriveKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeriveKeyOpRequestPayload Ttlv Serialization Tests")
class DeriveKeyOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DeriveKeyOpRequestPayload> {

    @Override
    public Class<DeriveKeyOpRequestPayload> type() {
        return DeriveKeyOpRequestPayload.class;
    }

    @Override
    public DeriveKeyOpRequestPayload createDefault() {
        return DeriveKeyOpRequestPayload.builder().build();
    }

    @Override
    public DeriveKeyOpRequestPayload createVariant() {
        return DeriveKeyOpRequestPayload.builder().build();
    }
}