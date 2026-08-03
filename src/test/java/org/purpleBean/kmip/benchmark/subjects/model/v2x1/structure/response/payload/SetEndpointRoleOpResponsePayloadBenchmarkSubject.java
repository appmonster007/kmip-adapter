package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetEndpointRoleOpResponsePayload;

public class SetEndpointRoleOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetEndpointRoleOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SetEndpointRoleOpResponsePayloadBenchmarkSubject() throws Exception {
    SetEndpointRoleOpResponsePayload subject = SetEndpointRoleOpResponsePayload
        .builder()
        .endpointRole(EndpointRole.Standard.SERVER.inst())
        .build();
    initialize(subject, SetEndpointRoleOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetEndpointRoleOpResponsePayload";
  }
}