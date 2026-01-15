package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleBenchmarkSubject extends KmipBenchmarkSubject<EndpointRole> {

    public EndpointRoleBenchmarkSubject() throws Exception {
        EndpointRole endpointRole = EndpointRole.Standard.CLIENT.inst();
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
