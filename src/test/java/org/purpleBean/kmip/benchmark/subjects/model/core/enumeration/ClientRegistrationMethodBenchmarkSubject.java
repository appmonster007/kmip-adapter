package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

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
