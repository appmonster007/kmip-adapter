package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyTemplateAttribute> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public PublicKeyTemplateAttributeBenchmarkSubject() throws Exception {
        PublicKeyTemplateAttribute subject = PublicKeyTemplateAttribute.of("default-string");  // TODO: Create a default instance
        initialize(subject, PublicKeyTemplateAttribute.class);
    }

    @Override
    public String name() {
        return "PublicKeyTemplateAttribute";
    }
}