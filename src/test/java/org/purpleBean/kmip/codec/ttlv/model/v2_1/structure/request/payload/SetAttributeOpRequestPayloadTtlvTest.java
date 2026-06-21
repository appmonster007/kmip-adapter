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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetAttributeOpRequestPayload Ttlv Serialization Tests")
class SetAttributeOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SetAttributeOpRequestPayload> {

    @Override
    public Class<SetAttributeOpRequestPayload> type() {
        return SetAttributeOpRequestPayload.class;
    }

    @Override
    public SetAttributeOpRequestPayload createDefault() {
        return SetAttributeOpRequestPayload.builder().build();
    }

    @Override
    public SetAttributeOpRequestPayload createVariant() {
        return SetAttributeOpRequestPayload.builder().build();
    }
}