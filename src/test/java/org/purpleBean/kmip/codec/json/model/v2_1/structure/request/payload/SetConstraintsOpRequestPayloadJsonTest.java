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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetConstraintsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetConstraintsOpRequestPayload Json Serialization Tests")
class SetConstraintsOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<SetConstraintsOpRequestPayload> {

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