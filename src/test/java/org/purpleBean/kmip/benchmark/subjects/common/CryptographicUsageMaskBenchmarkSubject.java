package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CryptographicUsageMask;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CryptographicUsageMaskBenchmarkSubject extends KmipBenchmarkSubject<CryptographicUsageMask> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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
