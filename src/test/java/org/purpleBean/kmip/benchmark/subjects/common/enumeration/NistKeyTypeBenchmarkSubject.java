package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeBenchmarkSubject extends KmipBenchmarkSubject<NistKeyType> {

    public NistKeyTypeBenchmarkSubject() throws Exception {
        NistKeyType nistKeyType = new NistKeyType(NistKeyType.Standard.PRIVATE_SIGNATURE_KEY);
        initialize(nistKeyType, NistKeyType.class);
    }

    @Override
    public String name() {
        return "NistKeyType";
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
