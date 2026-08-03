package org.purplebean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetEndpointRoleOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SetEndpointRoleOpRequestPayload Xml Serialization Tests")
class SetEndpointRoleOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<SetEndpointRoleOpRequestPayload> {

  @Override
  public Class<SetEndpointRoleOpRequestPayload> type() {
    return SetEndpointRoleOpRequestPayload.class;
  }

  @Override
  public SetEndpointRoleOpRequestPayload createDefault() {
    return SetEndpointRoleOpRequestPayload
        .builder()
        .endpointRole(EndpointRole.Standard.CLIENT.inst())
        .build();
  }

  @Override
  public SetEndpointRoleOpRequestPayload createVariant() {
    return SetEndpointRoleOpRequestPayload
        .builder()
        .endpointRole(EndpointRole.Standard.SERVER.inst())
        .build();
  }
}