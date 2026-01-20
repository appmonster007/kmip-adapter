package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PrivateKeyTemplateAttribute;

public class PrivateKeyTemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<PrivateKeyTemplateAttribute> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public PrivateKeyTemplateAttributeBenchmarkSubject() throws Exception {
        PrivateKeyTemplateAttribute subject = PrivateKeyTemplateAttribute.of("default-string");  // TODO: Create a default instance
        initialize(subject, PrivateKeyTemplateAttribute.class);
    }

    @Override
    public String name() {
        return "PrivateKeyTemplateAttribute";
    }
}