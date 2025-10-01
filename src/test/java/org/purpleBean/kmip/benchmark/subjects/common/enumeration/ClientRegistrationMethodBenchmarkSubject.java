package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

public class ClientRegistrationMethodBenchmarkSubject extends KmipBenchmarkSubject<ClientRegistrationMethod> {

    public ClientRegistrationMethodBenchmarkSubject() throws Exception {
        ClientRegistrationMethod clientRegistrationMethod = new ClientRegistrationMethod(ClientRegistrationMethod.Standard.UNSPECIFIED);
        initialize(clientRegistrationMethod, ClientRegistrationMethod.class);
    }

    @Override
    public String name() {
        return "ClientRegistrationMethod";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
