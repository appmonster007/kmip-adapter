package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.EndpointRole;

/**
 * Benchmark subject for {@link EndpointRole}.
 */
public class EndpointRoleBenchmarkSubject extends KmipBenchmarkSubject<EndpointRole> {

  /**
   * Constructs a new {@link EndpointRoleBenchmarkSubject}.
   */
  public EndpointRoleBenchmarkSubject() throws Exception {
    EndpointRole endpointRole = EndpointRole.Standard.CLIENT.inst();
    initialize(endpointRole, EndpointRole.class);
  }

  @Override
  public String name() {
    return "EndpointRole";
  }

}
