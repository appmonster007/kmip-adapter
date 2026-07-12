package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

import java.util.Collections;

public class ConstraintsBenchmarkSubject extends KmipBenchmarkSubject<Constraints> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public ConstraintsBenchmarkSubject() throws Exception {
        Constraints subject = Constraints.of(Collections.emptyList());
        initialize(subject, Constraints.class);
    }

    @Override
    public String name() {
        return "Constraints";
    }
}
