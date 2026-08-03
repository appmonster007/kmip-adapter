package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.model.v2x1.type.RequestCount;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LoginOpRequestPayload Xml Serialization Tests")
class LoginOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<LoginOpRequestPayload> {

  @Override
  public Class<LoginOpRequestPayload> type() {
    return LoginOpRequestPayload.class;
  }

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }


  @Override
  public LoginOpRequestPayload createDefault() {
    return LoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .build();
  }

  @Override
  public LoginOpRequestPayload createVariant() {
    return LoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(7200))
        .requestCount(RequestCount.of(20))
        .build();
  }
}