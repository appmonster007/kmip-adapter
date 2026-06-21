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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustAttributeOpRequestPayload Ttlv Serialization Tests")
class AdjustAttributeOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<AdjustAttributeOpRequestPayload> {

    @Override
    public Class<AdjustAttributeOpRequestPayload> type() {
        return AdjustAttributeOpRequestPayload.class;
    }

    @Override
    public AdjustAttributeOpRequestPayload createDefault() {
        return AdjustAttributeOpRequestPayload.builder().build();
    }

    @Override
    public AdjustAttributeOpRequestPayload createVariant() {
        return AdjustAttributeOpRequestPayload.builder().build();
    }
}