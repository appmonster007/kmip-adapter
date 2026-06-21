package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.ObliterateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObliterateOpRequestPayload Json Serialization Tests")
class ObliterateOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<ObliterateOpRequestPayload> {

    @Override
    public Class<ObliterateOpRequestPayload> type() {
        return ObliterateOpRequestPayload.class;
    }

    @Override
    public ObliterateOpRequestPayload createDefault() {
        return ObliterateOpRequestPayload.builder().build();
    }

    @Override
    public ObliterateOpRequestPayload createVariant() {
        return ObliterateOpRequestPayload.builder().build();
    }
}