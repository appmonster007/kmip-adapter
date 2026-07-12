package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.util.Collections;

public class AttributesBenchmarkSubject extends KmipBenchmarkSubject<Attributes> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public AttributesBenchmarkSubject() throws Exception {
        Attributes subject = Attributes.of(Collections.emptyList());
        initialize(subject, Attributes.class);
    }

    @Override
    public String name() {
        return "Attributes";
    }
}
