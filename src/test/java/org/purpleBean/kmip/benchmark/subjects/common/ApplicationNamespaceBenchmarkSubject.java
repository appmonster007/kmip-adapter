package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ApplicationNamespace;

public class ApplicationNamespaceBenchmarkSubject extends KmipBenchmarkSubject<ApplicationNamespace> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public ApplicationNamespaceBenchmarkSubject() throws Exception {
        ApplicationNamespace applicationNamespace = ApplicationNamespace.builder().value("test-namespace").build();
        initialize(applicationNamespace, ApplicationNamespace.class);
    }

    @Override
    public String name() {
        return "ApplicationNamespace";
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