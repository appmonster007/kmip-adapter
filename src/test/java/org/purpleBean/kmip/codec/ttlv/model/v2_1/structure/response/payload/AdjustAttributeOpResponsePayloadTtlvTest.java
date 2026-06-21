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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.AdjustAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustAttributeOpResponsePayload Ttlv Serialization Tests")
class AdjustAttributeOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<AdjustAttributeOpResponsePayload> {

    @Override
    public Class<AdjustAttributeOpResponsePayload> type() {
        return AdjustAttributeOpResponsePayload.class;
    }

    @Override
    public AdjustAttributeOpResponsePayload createDefault() {
        return AdjustAttributeOpResponsePayload.builder().build();
    }

    @Override
    public AdjustAttributeOpResponsePayload createVariant() {
        return AdjustAttributeOpResponsePayload.builder().build();
    }
}