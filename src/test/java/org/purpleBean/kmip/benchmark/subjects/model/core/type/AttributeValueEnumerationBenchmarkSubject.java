package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;

public class AttributeValueEnumerationBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueEnumeration> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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
