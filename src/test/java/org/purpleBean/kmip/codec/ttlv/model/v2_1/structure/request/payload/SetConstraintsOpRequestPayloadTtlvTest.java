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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetConstraintsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetConstraintsOpRequestPayload Ttlv Serialization Tests")
class SetConstraintsOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SetConstraintsOpRequestPayload> {

    @Override
    public Class<SetConstraintsOpRequestPayload> type() {
        return SetConstraintsOpRequestPayload.class;
    }

    @Override
    public SetConstraintsOpRequestPayload createDefault() {
        return SetConstraintsOpRequestPayload.builder().build();
    }

    @Override
    public SetConstraintsOpRequestPayload createVariant() {
        return SetConstraintsOpRequestPayload.builder().build();
    }
}