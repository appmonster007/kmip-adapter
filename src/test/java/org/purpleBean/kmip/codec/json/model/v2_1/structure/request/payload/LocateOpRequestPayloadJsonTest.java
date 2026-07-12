package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LocateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Json Serialization Tests")
class LocateOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<LocateOpRequestPayload> {

    @Override
    public Class<LocateOpRequestPayload> type() {
        return LocateOpRequestPayload.class;
    }

    @Override
    public LocateOpRequestPayload createDefault() {
        return LocateOpRequestPayload.builder().build();
    }

    @Override
    public LocateOpRequestPayload createVariant() {
        return LocateOpRequestPayload.builder().build();
    }
}