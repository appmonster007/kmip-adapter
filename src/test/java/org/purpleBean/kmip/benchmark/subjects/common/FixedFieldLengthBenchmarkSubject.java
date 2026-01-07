package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.FixedFieldLength;

public class FixedFieldLengthBenchmarkSubject extends KmipBenchmarkSubject<FixedFieldLength> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public FixedFieldLengthBenchmarkSubject() throws Exception {
        FixedFieldLength fixedFieldLength = FixedFieldLength.of(128);
        initialize(fixedFieldLength, FixedFieldLength.class);
    }

    @Override
    public String name() {
        return "FixedFieldLength";
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