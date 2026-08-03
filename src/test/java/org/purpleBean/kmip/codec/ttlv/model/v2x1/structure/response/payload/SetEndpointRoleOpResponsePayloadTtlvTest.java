package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetEndpointRoleOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetEndpointRoleOpResponsePayload Ttlv Serialization Tests")
class SetEndpointRoleOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetEndpointRoleOpResponsePayload> {

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