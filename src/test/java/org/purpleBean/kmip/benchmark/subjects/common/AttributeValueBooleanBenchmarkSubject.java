package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueBoolean> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueBooleanBenchmarkSubject() throws Exception {
        AttributeValueBoolean attributeValueBoolean = AttributeValueBoolean.of(true);
        initialize(attributeValueBoolean, AttributeValueBoolean.class);
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
