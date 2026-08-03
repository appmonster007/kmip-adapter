package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetEndpointRoleOpRequestPayload Json Serialization Tests")
class SetEndpointRoleOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetEndpointRoleOpRequestPayload> {

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