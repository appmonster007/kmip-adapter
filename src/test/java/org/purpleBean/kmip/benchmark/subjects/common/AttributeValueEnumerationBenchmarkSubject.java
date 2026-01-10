package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueEnumeration> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueEnumerationBenchmarkSubject() throws Exception {
        AttributeValueEnumeration attributeValueEnumeration = AttributeValueEnumeration.of(123);
        initialize(attributeValueEnumeration, AttributeValueEnumeration.class);
    }

    @Override
    public String name() {
        return "AttributeValueEnumeration";
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
