package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.v2x1.structure.request.RequestHeader;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestHeader Ttlv Serialization Tests")
class RequestHeaderTtlvTest extends AbstractTtlvSerializationTestSuite<RequestHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<RequestHeader> type() {
    return RequestHeader.class;
  }

  @Override
  public RequestHeader createDefault() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .batchCount(BatchCount.of(1))
        .build();
  }

  @Override
  public RequestHeader createVariant() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .batchCount(BatchCount.of(2))
        .build();
  }
}
