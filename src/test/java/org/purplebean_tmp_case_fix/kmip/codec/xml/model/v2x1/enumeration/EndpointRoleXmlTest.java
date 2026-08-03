package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EndpointRole XML Serialization")
class EndpointRoleXmlTest extends AbstractXmlSerializationTestSuite<EndpointRole> {
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
