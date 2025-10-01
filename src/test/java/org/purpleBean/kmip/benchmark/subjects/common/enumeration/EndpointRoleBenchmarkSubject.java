package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleBenchmarkSubject extends KmipBenchmarkSubject<EndpointRole> {

    public EndpointRoleBenchmarkSubject() throws Exception {
        EndpointRole endpointRole = new EndpointRole(EndpointRole.Standard.CLIENT);
        initialize(endpointRole, EndpointRole.class);
    }

    @Override
    public String name() {
        return "EndpointRole";
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
