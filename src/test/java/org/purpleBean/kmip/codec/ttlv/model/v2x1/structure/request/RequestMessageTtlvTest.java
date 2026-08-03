package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.v2x1.structure.request.RequestHeader;
import org.purplebean.kmip.model.v2x1.structure.request.RequestMessage;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestMessage Ttlv Serialization Tests")
class RequestMessageTtlvTest extends AbstractTtlvSerializationTestSuite<RequestMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<RequestMessage> type() {
    return RequestMessage.class;
  }

  private RequestHeader header() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .batchCount(BatchCount.of(0))
        .build();
  }

  @Override
  public RequestMessage createDefault() {
    return RequestMessage
        .builder()
        .requestHeader(header())
        .build();
  }

  @Override
  public RequestMessage createVariant() {
    return RequestMessage
        .builder()
        .requestHeader(header())
        .build();
  }
}
