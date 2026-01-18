package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeBenchmarkSubject extends KmipBenchmarkSubject<NistKeyType> {

    public NistKeyTypeBenchmarkSubject() throws Exception {
        NistKeyType nistKeyType = NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
        initialize(nistKeyType, NistKeyType.class);
    }

    @Override
    public String name() {
        return "NistKeyType";
    }

}
