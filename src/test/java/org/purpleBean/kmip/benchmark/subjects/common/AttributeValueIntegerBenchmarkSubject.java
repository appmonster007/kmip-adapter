package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueInteger> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueIntegerBenchmarkSubject() throws Exception {
        AttributeValueInteger attributeValueInteger = AttributeValueInteger.of(123);
        initialize(attributeValueInteger, AttributeValueInteger.class);
    }

    @Override
    public String name() {
        return "AttributeValueInteger";
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
