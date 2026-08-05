package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetEndpointRoleOpRequestPayload;

/**
 * Benchmark subject for {@link SetEndpointRoleOpRequestPayload}.
 */
public class SetEndpointRoleOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetEndpointRoleOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SetEndpointRoleOpRequestPayloadBenchmarkSubject}.
   */
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