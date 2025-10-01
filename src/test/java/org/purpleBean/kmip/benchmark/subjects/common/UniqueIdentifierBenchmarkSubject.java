package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

    public UniqueIdentifierBenchmarkSubject() throws Exception {
        UniqueIdentifier uniqueIdentifier = UniqueIdentifier.builder().value("FIXED_STRING").build();
        initialize(uniqueIdentifier, UniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "UniqueIdentifier";
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
