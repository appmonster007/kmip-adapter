package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceBenchmarkSubject extends KmipBenchmarkSubject<ApplicationNamespace> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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