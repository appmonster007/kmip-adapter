package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Salt;

public class SaltBenchmarkSubject extends KmipBenchmarkSubject<Salt> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public SaltBenchmarkSubject() throws Exception {
        Salt salt = Salt.of(new byte[]{0x01, 0x02, 0x03});
        initialize(salt, Salt.class);
    }

    @Override
    public String name() {
        return "Salt";
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