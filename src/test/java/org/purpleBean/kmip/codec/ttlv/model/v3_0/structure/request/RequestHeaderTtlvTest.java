package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.MaximumResponseSize;
import org.purpleBean.kmip.model.v3_0.structure.request.RequestHeader;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestHeader Ttlv Serialization Tests")
class RequestHeaderTtlvTest extends AbstractTtlvSerializationTestSuite<RequestHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  public Class<RequestHeader> type() {
    return RequestHeader.class;
  }

  @Override
  public RequestHeader createDefault() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .build();
  }

  @Override
  public RequestHeader createVariant() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .maximumResponseSize(MaximumResponseSize.of(1024))
        .build();
  }
}