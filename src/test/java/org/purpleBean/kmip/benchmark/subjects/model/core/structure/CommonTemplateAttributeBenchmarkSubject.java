package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class CommonTemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<CommonTemplateAttribute> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CommonTemplateAttributeBenchmarkSubject() throws Exception {
        CommonTemplateAttribute subject = CommonTemplateAttribute.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
        initialize(subject, CommonTemplateAttribute.class);
    }

    @Override
    public String name() {
        return "CommonTemplateAttribute";
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