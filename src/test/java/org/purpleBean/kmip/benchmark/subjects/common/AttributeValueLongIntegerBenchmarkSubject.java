package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueLongInteger> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueLongIntegerBenchmarkSubject() throws Exception {
        AttributeValueLongInteger attributeValueLongInteger = AttributeValueLongInteger.of(123L);
        initialize(attributeValueLongInteger, AttributeValueLongInteger.class);
    }

    @Override
    public String name() {
        return "AttributeValueLongInteger";
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
