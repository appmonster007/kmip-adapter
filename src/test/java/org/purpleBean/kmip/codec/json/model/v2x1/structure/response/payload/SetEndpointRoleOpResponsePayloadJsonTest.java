package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetEndpointRoleOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetEndpointRoleOpResponsePayload Json Serialization Tests")
class SetEndpointRoleOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetEndpointRoleOpResponsePayload> {

  @Override
  public Class<SetEndpointRoleOpResponsePayload> type() {
    return SetEndpointRoleOpResponsePayload.class;
  }

  @Override
  public SetEndpointRoleOpResponsePayload createDefault() {
    return SetEndpointRoleOpResponsePayload
        .builder()
        .endpointRole(EndpointRole.Standard.SERVER.inst())
        .build();
  }

  @Override
  public SetEndpointRoleOpResponsePayload createVariant() {
    return SetEndpointRoleOpResponsePayload
        .builder()
        .endpointRole(EndpointRole.Standard.CLIENT.inst())
        .build();
  }
}