package org.purpleBean.kmip.codec.json.model.v2x1.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2x1.structure.response.ResponseHeader;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResponseHeader Json Serialization Tests")
class ResponseHeaderJsonTest extends AbstractJsonSerializationTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  public ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .batchCount(BatchCount.of(1))
        .build();
  }

  @Override
  public ResponseHeader createVariant() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .batchCount(BatchCount.of(2))
        .build();
  }
}
