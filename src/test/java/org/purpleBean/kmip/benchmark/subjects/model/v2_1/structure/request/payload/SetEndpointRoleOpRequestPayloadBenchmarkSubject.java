package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetEndpointRoleOpRequestPayload;

public class SetEndpointRoleOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetEndpointRoleOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SetEndpointRoleOpRequestPayloadBenchmarkSubject() throws Exception {
    SetEndpointRoleOpRequestPayload subject = SetEndpointRoleOpRequestPayload
        .builder()
        .endpointRole(EndpointRole.Standard.CLIENT.inst())
        .build();
    initialize(subject, SetEndpointRoleOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetEndpointRoleOpRequestPayload";
  }
}