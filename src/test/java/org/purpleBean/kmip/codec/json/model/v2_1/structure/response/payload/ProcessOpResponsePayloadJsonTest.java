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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProcessOpResponsePayload Json Serialization Tests")
class ProcessOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<ProcessOpResponsePayload> {

    @Override
    public Class<ProcessOpResponsePayload> type() {
        return ProcessOpResponsePayload.class;
    }

    @Override
    public ProcessOpResponsePayload createDefault() {
        return ProcessOpResponsePayload.builder().build();
    }

    @Override
    public ProcessOpResponsePayload createVariant() {
        return ProcessOpResponsePayload.builder().build();
    }
}