package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v3x0.structure.response.ResponseHeader;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResponseHeader Ttlv Serialization Tests")
class ResponseHeaderTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  public Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  public ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .build();
  }

  @Override
  public ResponseHeader createVariant() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2025, 6, 15, 0, 0, 0, 0, ZoneOffset.UTC)))
        .build();
  }
}