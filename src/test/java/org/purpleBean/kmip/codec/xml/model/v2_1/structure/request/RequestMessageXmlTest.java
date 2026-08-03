package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v2_1.structure.request.RequestHeader;
import org.purpleBean.kmip.model.v2_1.structure.request.RequestMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RequestMessage Xml Serialization Tests")
class RequestMessageXmlTest extends AbstractXmlSerializationTestSuite<RequestMessage> {

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
