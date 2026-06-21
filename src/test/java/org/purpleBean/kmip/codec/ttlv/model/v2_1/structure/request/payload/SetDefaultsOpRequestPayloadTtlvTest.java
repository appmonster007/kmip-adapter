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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetDefaultsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetDefaultsOpRequestPayload Ttlv Serialization Tests")
class SetDefaultsOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SetDefaultsOpRequestPayload> {

    @Override
    public Class<SetDefaultsOpRequestPayload> type() {
        return SetDefaultsOpRequestPayload.class;
    }

    @Override
    public SetDefaultsOpRequestPayload createDefault() {
        return SetDefaultsOpRequestPayload.builder().build();
    }

    @Override
    public SetDefaultsOpRequestPayload createVariant() {
        return SetDefaultsOpRequestPayload.builder().build();
    }
}