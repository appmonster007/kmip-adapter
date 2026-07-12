package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;

import java.util.Collections;

public class PrivateKeyAttributesBenchmarkSubject extends KmipBenchmarkSubject<PrivateKeyAttributes> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public PrivateKeyAttributesBenchmarkSubject() throws Exception {
        PrivateKeyAttributes subject = PrivateKeyAttributes.of(Collections.emptyList());
        initialize(subject, PrivateKeyAttributes.class);
    }

    @Override
    public String name() {
        return "PrivateKeyAttributes";
    }
}
