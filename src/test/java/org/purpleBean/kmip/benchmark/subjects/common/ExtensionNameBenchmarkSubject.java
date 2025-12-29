package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ExtensionName;

public class ExtensionNameBenchmarkSubject extends KmipBenchmarkSubject<ExtensionName> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public ExtensionNameBenchmarkSubject() throws Exception {
        ExtensionName extensionName = ExtensionName.builder().value("test-extension").build();
        initialize(extensionName, ExtensionName.class);
    }

    @Override
    public String name() {
        return "ExtensionName";
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