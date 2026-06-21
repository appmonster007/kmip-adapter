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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ProcessOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProcessOpRequestPayload Ttlv Serialization Tests")
class ProcessOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ProcessOpRequestPayload> {

    @Override
    public Class<ProcessOpRequestPayload> type() {
        return ProcessOpRequestPayload.class;
    }

    @Override
    public ProcessOpRequestPayload createDefault() {
        return ProcessOpRequestPayload.builder().build();
    }

    @Override
    public ProcessOpRequestPayload createVariant() {
        return ProcessOpRequestPayload.builder().build();
    }
}