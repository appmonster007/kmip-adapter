package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ExtensionType;

public class ExtensionTypeBenchmarkSubject extends KmipBenchmarkSubject<ExtensionType> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ExtensionTypeBenchmarkSubject() throws Exception {
        ExtensionType extensionType = ExtensionType.builder().value(1).build();
        initialize(extensionType, ExtensionType.class);
    }

    @Override
    public String name() {
        return "ExtensionType";
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