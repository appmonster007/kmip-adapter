package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

    public UniqueIdentifierBenchmarkSubject() throws Exception {
        UniqueIdentifier uniqueIdentifier = UniqueIdentifier.builder().value("FIXED_STRING").build();
        initialize(uniqueIdentifier, UniqueIdentifier.class);
    }

    @Override
    public String name() {
        return "UniqueIdentifier";
    }

}
