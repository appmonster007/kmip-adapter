package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.KeyWrappingData;

public class KeyWrappingDataBenchmarkSubject extends KmipBenchmarkSubject<KeyWrappingData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public KeyWrappingDataBenchmarkSubject() throws Exception {
        KeyWrappingData subject = KeyWrappingData.builder()
                .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
                .build();
        initialize(subject, KeyWrappingData.class);
    }

    @Override
    public String name() {
        return "KeyWrappingData";
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