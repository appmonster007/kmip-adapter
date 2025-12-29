package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;

public class LinkedObjectIdentifierBenchmarkSubject extends KmipBenchmarkSubject<LinkedObjectIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public LinkedObjectIdentifierBenchmarkSubject() throws Exception {
        LinkedObjectIdentifier linkedObjectIdentifier = LinkedObjectIdentifier.builder().value("test-linked-id").build();
        initialize(linkedObjectIdentifier, LinkedObjectIdentifier.class);
    }

    @Override
    public String name() {
        return "LinkedObjectIdentifier";
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