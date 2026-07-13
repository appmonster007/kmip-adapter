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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacOpRequestPayload Ttlv Serialization Tests")
class MacOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacOpRequestPayload> {

    @Override
    public Class<MacOpRequestPayload> type() {
        return MacOpRequestPayload.class;
    }

    @Override
    public MacOpRequestPayload createDefault() {
        return MacOpRequestPayload.builder().build();
    }

    @Override
    public MacOpRequestPayload createVariant() {
        return MacOpRequestPayload.builder().build();
    }
}