package org.purpleBean.kmip.codec.json.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ObliterateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObliterateOpResponsePayload Json Serialization Tests")
class ObliterateOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<ObliterateOpResponsePayload> {

    @Override
    public Class<ObliterateOpResponsePayload> type() {
        return ObliterateOpResponsePayload.class;
    }

    @Override
    public ObliterateOpResponsePayload createDefault() {
        return ObliterateOpResponsePayload.builder().build();
    }

    @Override
    public ObliterateOpResponsePayload createVariant() {
        return ObliterateOpResponsePayload.builder().build();
    }
}