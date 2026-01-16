package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class PublicKeyTemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyTemplateAttribute> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public PublicKeyTemplateAttributeBenchmarkSubject() throws Exception {
        PublicKeyTemplateAttribute subject = PublicKeyTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
        initialize(subject, PublicKeyTemplateAttribute.class);
    }

    @Override
    public String name() {
        return "PublicKeyTemplateAttribute";
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