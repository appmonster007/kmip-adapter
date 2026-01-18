package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Fresh;

public class FreshBenchmarkSubject extends KmipBenchmarkSubject<Fresh> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public FreshBenchmarkSubject() throws Exception {
        Fresh fresh = Fresh.builder().value(true).build();
        initialize(fresh, Fresh.class);
    }

    @Override
    public String name() {
        return "Fresh";
    }

}