package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueEnumerationBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.Enumeration> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueEnumerationBenchmarkSubject() throws Exception {
        AttributeValue.Enumeration attributeValueEnumeration = AttributeValue.Enumeration.of(123);
        initialize(attributeValueEnumeration, AttributeValue.Enumeration.class);
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
