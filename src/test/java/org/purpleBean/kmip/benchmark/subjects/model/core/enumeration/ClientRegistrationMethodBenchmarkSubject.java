package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodBenchmarkSubject
    extends KmipBenchmarkSubject<ClientRegistrationMethod> {

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
