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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReProvisionOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReProvisionOpRequestPayload Ttlv Serialization Tests")
class ReProvisionOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ReProvisionOpRequestPayload> {

    @Override
    public Class<ReProvisionOpRequestPayload> type() {
        return ReProvisionOpRequestPayload.class;
    }

    @Override
    public ReProvisionOpRequestPayload createDefault() {
        return ReProvisionOpRequestPayload.of(UniqueIdentifier.builder().value("test-uid-1").build());
    }

    @Override
    public ReProvisionOpRequestPayload createVariant() {
        return ReProvisionOpRequestPayload.of(UniqueIdentifier.builder().value("test-uid-variant").build());
    }
}