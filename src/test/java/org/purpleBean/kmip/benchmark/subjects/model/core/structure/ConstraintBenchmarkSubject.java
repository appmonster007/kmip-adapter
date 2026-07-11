package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Constraint;

import java.util.Collections;

public class ConstraintBenchmarkSubject extends KmipBenchmarkSubject<Constraint> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public ConstraintBenchmarkSubject() throws Exception {
        Constraint subject = Constraint.of(Collections.emptyList());
        initialize(subject, Constraint.class);
    }

    @Override
    public String name() {
        return "Constraint";
    }
}
