package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3_0.structure.Name;

public class NameBenchmarkSubject extends KmipBenchmarkSubject<Name> {

    @Getter
    private KmipSpec spec = KmipSpec.V3_0;

    public NameBenchmarkSubject() throws Exception {
        Name subject = Name.of("benchmark-name");
        initialize(subject, Name.class);
    }

    @Override
    public String name() {
        return "Name";
    }
}
