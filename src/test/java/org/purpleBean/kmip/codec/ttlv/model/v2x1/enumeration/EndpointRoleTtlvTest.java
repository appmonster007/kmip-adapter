package org.purpleBean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("EndpointRole TTLV Serialization")
class EndpointRoleTtlvTest extends AbstractTtlvSerializationTestSuite<EndpointRole> {
  @Override
  public Class<EndpointRole> type() {
    return EndpointRole.class;
  }

  @Override
  public EndpointRole createDefault() {
    return EndpointRole.Standard.CLIENT.inst();
  }

  @Override
  public EndpointRole createVariant() {
    return EndpointRole.Standard.SERVER.inst();
  }
}
