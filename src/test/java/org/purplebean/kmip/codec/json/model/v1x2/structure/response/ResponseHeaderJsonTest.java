package org.purplebean.kmip.codec.json.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResponseHeader Json Serialization Tests")
class ResponseHeaderJsonTest extends AbstractJsonSerializationTestSuite<ResponseHeader> {

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
