package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueLongIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.LongInteger> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueLongIntegerBenchmarkSubject() throws Exception {
        AttributeValue.LongInteger attributeValueLongInteger = AttributeValue.LongInteger.of(123L);
        initialize(attributeValueLongInteger, AttributeValue.LongInteger.class);
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
