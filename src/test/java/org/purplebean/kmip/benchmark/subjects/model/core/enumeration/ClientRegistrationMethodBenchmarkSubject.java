package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ClientRegistrationMethod;

/**
 * Benchmark subject for {@link ClientRegistrationMethod}.
 */
public class ClientRegistrationMethodBenchmarkSubject
    extends KmipBenchmarkSubject<ClientRegistrationMethod> {

  /**
   * Constructs a new {@link ClientRegistrationMethodBenchmarkSubject}.
   */
  public ClientRegistrationMethodBenchmarkSubject() throws Exception {
    ClientRegistrationMethod clientRegistrationMethod =
        ClientRegistrationMethod.Standard.UNSPECIFIED.inst();
    initialize(clientRegistrationMethod, ClientRegistrationMethod.class);
  }

  @Override
  public String name() {
    return "ClientRegistrationMethod";
  }

}
