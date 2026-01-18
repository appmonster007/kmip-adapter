package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.Template;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class TemplateBenchmarkSubject extends KmipBenchmarkSubject<Template> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public TemplateBenchmarkSubject() throws Exception {
        Template subject = Template.builder()
                .attribute(Attribute.builder()
                        .attributeName(AttributeName.of("test-attribute"))
                        .attributeValue(AttributeValueInteger.of(1))
                        .build())
                .build();
        initialize(subject, Template.class);
    }

    @Override
    public String name() {
        return "Template";
    }

}