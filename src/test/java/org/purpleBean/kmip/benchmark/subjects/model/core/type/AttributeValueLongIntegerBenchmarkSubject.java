package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

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
