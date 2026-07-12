package org.purpleBean.kmip.benchmark.subjects.model.v2_1.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.EndpointRole;

public class EndpointRoleBenchmarkSubject extends KmipBenchmarkSubject<EndpointRole> {

    public EndpointRoleBenchmarkSubject() throws Exception {
        EndpointRole endpointRole = EndpointRole.Standard.CLIENT.inst();
        initialize(endpointRole, EndpointRole.class);
    }

    @Override
    public String name() {
        return "EndpointRole";
    }

}
