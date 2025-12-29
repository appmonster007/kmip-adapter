package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

public class ReplacedUniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<ReplacedUniqueIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public ReplacedUniqueIdentifierBenchmarkSubject() throws Exception {
        ReplacedUniqueIdentifier replacedUniqueIdentifier = ReplacedUniqueIdentifier.builder().value("test-id").build();
        initialize(replacedUniqueIdentifier, ReplacedUniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "ReplacedUniqueIdentifier";
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