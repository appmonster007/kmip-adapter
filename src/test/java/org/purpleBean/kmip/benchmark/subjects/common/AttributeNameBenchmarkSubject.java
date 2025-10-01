package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameBenchmarkSubject extends KmipBenchmarkSubject<AttributeName> {

    public AttributeNameBenchmarkSubject() throws Exception {
        AttributeName attributeName = AttributeName.builder().value("attribute name").build();
        initialize(attributeName, AttributeName.class);
    }

    @Override
    public String name() {
        return "AttributeName";
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
