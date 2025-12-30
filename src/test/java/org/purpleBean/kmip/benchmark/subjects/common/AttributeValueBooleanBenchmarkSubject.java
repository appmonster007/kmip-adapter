package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueBooleanBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.Boolean> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueBooleanBenchmarkSubject() throws Exception {
        AttributeValue.Boolean attributeValueBoolean = AttributeValue.Boolean.of(true);
        initialize(attributeValueBoolean, AttributeValue.Boolean.class);
    }

    @Override
    public String name() {
        return "AttributeValueBoolean";
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
