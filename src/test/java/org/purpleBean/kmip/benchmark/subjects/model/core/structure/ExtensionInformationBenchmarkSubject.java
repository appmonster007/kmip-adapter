package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ExtensionInformation;
import org.purpleBean.kmip.model.core.type.ExtensionName;

public class ExtensionInformationBenchmarkSubject extends KmipBenchmarkSubject<ExtensionInformation> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public ExtensionInformationBenchmarkSubject() throws Exception {
        ExtensionInformation subject = ExtensionInformation.builder()
                .extensionName(ExtensionName.of("test-extension"))
                .build();
        initialize(subject, ExtensionInformation.class);
    }

    @Override
    public String name() {
        return "ExtensionInformation";
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