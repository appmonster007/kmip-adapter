package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetEndpointRoleOpResponsePayload;

/**
 * Benchmark subject for {@link SetEndpointRoleOpResponsePayload}.
 */
public class SetEndpointRoleOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetEndpointRoleOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SetEndpointRoleOpResponsePayloadBenchmarkSubject}.
   */
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