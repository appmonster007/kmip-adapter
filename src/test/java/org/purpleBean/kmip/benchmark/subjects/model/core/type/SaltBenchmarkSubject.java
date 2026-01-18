package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Salt;

public class SaltBenchmarkSubject extends KmipBenchmarkSubject<Salt> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public SaltBenchmarkSubject() throws Exception {
        Salt salt = Salt.of(new byte[]{0x01, 0x02, 0x03});
        initialize(salt, Salt.class);
    }

    @Override
    public String name() {
        return "Salt";
    }

}