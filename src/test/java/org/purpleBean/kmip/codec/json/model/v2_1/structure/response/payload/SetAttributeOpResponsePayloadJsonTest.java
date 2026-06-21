package org.purpleBean.kmip.codec.json.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetAttributeOpResponsePayload Json Serialization Tests")
class SetAttributeOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<SetAttributeOpResponsePayload> {

    @Override
    public Class<SetAttributeOpResponsePayload> type() {
        return SetAttributeOpResponsePayload.class;
    }

    @Override
    public SetAttributeOpResponsePayload createDefault() {
        return SetAttributeOpResponsePayload.builder().build();
    }

    @Override
    public SetAttributeOpResponsePayload createVariant() {
        return SetAttributeOpResponsePayload.builder().build();
    }
}