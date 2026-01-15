package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

public class CryptographicUsageMaskBenchmarkSubject extends KmipBenchmarkSubject<CryptographicUsageMask> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CryptographicUsageMaskBenchmarkSubject() throws Exception {
        var fixed = 10;
        CryptographicUsageMask cryptographicUsageMask = CryptographicUsageMask.builder().value(fixed).build();
        initialize(cryptographicUsageMask, CryptographicUsageMask.class);
    }

    @Override
    public String name() {
        return "CryptographicUsageMask";
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
