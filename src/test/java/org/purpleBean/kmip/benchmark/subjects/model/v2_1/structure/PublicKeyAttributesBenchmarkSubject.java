package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;

import java.util.Collections;

public class PublicKeyAttributesBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyAttributes> {

    @Getter
    private final KmipSpec spec = KmipSpec.V2_1;

    public PublicKeyAttributesBenchmarkSubject() throws Exception {
        PublicKeyAttributes subject = PublicKeyAttributes.of(Collections.emptyList());
        initialize(subject, PublicKeyAttributes.class);
    }

    @Override
    public String name() {
        return "PublicKeyAttributes";
    }
}
