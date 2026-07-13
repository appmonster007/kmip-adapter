package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ResponseMessage Ttlv Serialization Tests")
class ResponseMessageTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseMessage> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<ResponseMessage> type() {
        return ResponseMessage.class;
    }

    private ResponseHeader header() {
        return ResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(2, 1))
                .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
                .batchCount(BatchCount.of(0))
                .build();
    }

    @Override
    public ResponseMessage createDefault() {
        return ResponseMessage.builder().responseHeader(header()).build();
    }

    @Override
    public ResponseMessage createVariant() {
        return ResponseMessage.builder().responseHeader(header()).build();
    }
}
