package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ExportOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExportOpRequestPayload Ttlv Serialization Tests")
class ExportOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ExportOpRequestPayload> {

    @Override
    public Class<ExportOpRequestPayload> type() {
        return ExportOpRequestPayload.class;
    }

    @Override
    public ExportOpRequestPayload createDefault() {
        return ExportOpRequestPayload.builder().build();
    }

    @Override
    public ExportOpRequestPayload createVariant() {
        return ExportOpRequestPayload.builder().build();
    }
}