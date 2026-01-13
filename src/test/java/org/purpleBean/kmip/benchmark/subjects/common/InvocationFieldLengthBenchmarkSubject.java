package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.InvocationFieldLength;

public class InvocationFieldLengthBenchmarkSubject extends KmipBenchmarkSubject<InvocationFieldLength> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public InvocationFieldLengthBenchmarkSubject() throws Exception {
        InvocationFieldLength invocationFieldLength = InvocationFieldLength.of(128);
        initialize(invocationFieldLength, InvocationFieldLength.class);
    }

    @Override
    public String name() {
        return "InvocationFieldLength";
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