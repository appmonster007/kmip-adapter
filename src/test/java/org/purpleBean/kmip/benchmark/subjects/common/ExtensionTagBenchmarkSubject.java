package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ExtensionTag;

public class ExtensionTagBenchmarkSubject extends KmipBenchmarkSubject<ExtensionTag> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ExtensionTagBenchmarkSubject() throws Exception {
        ExtensionTag extensionTag = ExtensionTag.builder().value(1).build();
        initialize(extensionTag, ExtensionTag.class);
    }

    @Override
    public String name() {
        return "ExtensionTag";
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