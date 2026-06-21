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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReProvisionOpResponsePayload Ttlv Serialization Tests")
class ReProvisionOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ReProvisionOpResponsePayload> {

    @Override
    public Class<ReProvisionOpResponsePayload> type() {
        return ReProvisionOpResponsePayload.class;
    }

    @Override
    public ReProvisionOpResponsePayload createDefault() {
        return ReProvisionOpResponsePayload.builder().build();
    }

    @Override
    public ReProvisionOpResponsePayload createVariant() {
        return ReProvisionOpResponsePayload.builder().build();
    }
}