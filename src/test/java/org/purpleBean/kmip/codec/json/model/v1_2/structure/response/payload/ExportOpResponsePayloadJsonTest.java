package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ExportOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExportOpResponsePayload Json Serialization Tests")
class ExportOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<ExportOpResponsePayload> {

    @Override
    public Class<ExportOpResponsePayload> type() {
        return ExportOpResponsePayload.class;
    }

    @Override
    public ExportOpResponsePayload createDefault() {
        return ExportOpResponsePayload.builder().build();
    }

    @Override
    public ExportOpResponsePayload createVariant() {
        return ExportOpResponsePayload.builder().build();
    }
}