package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivateOpRequestPayload Ttlv Serialization Tests")
class DeactivateOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DeactivateOpRequestPayload> {

    @Override
    public Class<DeactivateOpRequestPayload> type() {
        return DeactivateOpRequestPayload.class;
    }

    @Override
    public DeactivateOpRequestPayload createDefault() {
        return DeactivateOpRequestPayload.builder().build();
    }

    @Override
    public DeactivateOpRequestPayload createVariant() {
        return DeactivateOpRequestPayload.builder().build();
    }
}