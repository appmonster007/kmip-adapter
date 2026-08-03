package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v1x2.structure.response.ResponseHeader;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResponseHeader Ttlv Serialization Tests")
class ResponseHeaderTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  public ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .timeStamp(TimeStamp.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .batchCount(BatchCount.of(1))
        .build();
  }

  @Override
  public ResponseHeader createVariant() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
        .timeStamp(TimeStamp.of(OffsetDateTime
            .now(ZoneOffset.UTC)
            .plusDays(1)))
        .batchCount(BatchCount.of(2))
        .build();
  }
}
