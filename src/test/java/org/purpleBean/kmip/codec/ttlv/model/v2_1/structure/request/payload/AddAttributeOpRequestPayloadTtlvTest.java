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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AddAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AddAttributeOpRequestPayload Ttlv Serialization Tests")
class AddAttributeOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<AddAttributeOpRequestPayload> {

    @Override
    public Class<AddAttributeOpRequestPayload> type() {
        return AddAttributeOpRequestPayload.class;
    }

    @Override
    public AddAttributeOpRequestPayload createDefault() {
        return AddAttributeOpRequestPayload.builder().build();
    }

    @Override
    public AddAttributeOpRequestPayload createVariant() {
        return AddAttributeOpRequestPayload.builder().build();
    }
}