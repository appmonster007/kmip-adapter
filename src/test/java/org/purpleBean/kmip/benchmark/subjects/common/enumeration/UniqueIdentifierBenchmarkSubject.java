package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

public class UniqueIdentifierBenchmarkSubject extends KmipBenchmarkSubject<UniqueIdentifier> {

    public UniqueIdentifierBenchmarkSubject() throws Exception {
        UniqueIdentifier uniqueIdentifier = new UniqueIdentifier(UniqueIdentifier.Standard.ID_PLACEHOLDER);
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
