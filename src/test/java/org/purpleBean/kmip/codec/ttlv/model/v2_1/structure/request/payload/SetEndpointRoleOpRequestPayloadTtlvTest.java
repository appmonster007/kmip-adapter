package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetEndpointRoleOpRequestPayload Ttlv Serialization Tests")
class SetEndpointRoleOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetEndpointRoleOpRequestPayload> {

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