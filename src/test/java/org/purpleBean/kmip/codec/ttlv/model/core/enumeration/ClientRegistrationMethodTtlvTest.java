package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ClientRegistrationMethod TTLV Serialization")
class ClientRegistrationMethodTtlvTest
    extends AbstractTtlvSerializationTestSuite<ClientRegistrationMethod> {
  @Override
  public Class<ClientRegistrationMethod> type() {
    return ClientRegistrationMethod.class;
  }

  @Override
  public ClientRegistrationMethod createDefault() {
    return ClientRegistrationMethod.Standard.UNSPECIFIED.inst();
  }

  @Override
  public ClientRegistrationMethod createVariant() {
    return ClientRegistrationMethod.Standard.SERVER_PRE_GENERATED.inst();
  }
}
