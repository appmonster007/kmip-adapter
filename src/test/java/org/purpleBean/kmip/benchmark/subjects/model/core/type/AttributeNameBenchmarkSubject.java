package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeName;

public class AttributeNameBenchmarkSubject extends KmipBenchmarkSubject<AttributeName> {

    public AttributeNameBenchmarkSubject() throws Exception {
        AttributeName attributeName = AttributeName.builder().value("attribute name").build();
        initialize(attributeName, AttributeName.class);
    }

    @Override
    public String name() {
        return "AttributeName";
    }

}
