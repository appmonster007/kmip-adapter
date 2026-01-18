package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

public class LinkedObjectIdentifierBenchmarkSubject extends KmipBenchmarkSubject<LinkedObjectIdentifier> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public LinkedObjectIdentifierBenchmarkSubject() throws Exception {
        LinkedObjectIdentifier linkedObjectIdentifier = LinkedObjectIdentifier.builder().value("test-linked-id").build();
        initialize(linkedObjectIdentifier, LinkedObjectIdentifier.class);
    }

    @Override
    public String name() {
        return "LinkedObjectIdentifier";
    }

}