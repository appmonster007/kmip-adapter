package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexBenchmarkSubject extends KmipBenchmarkSubject<AttributeIndex> {

    public AttributeIndexBenchmarkSubject() throws Exception {
        AttributeIndex attributeIndex = AttributeIndex.builder().value(10).build();
        initialize(attributeIndex, AttributeIndex.class);
    }

    @Override
    public String name() {
        return "AttributeIndex";
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
