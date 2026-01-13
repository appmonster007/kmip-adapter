package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueBigInteger> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueBigIntegerBenchmarkSubject() throws Exception {
        AttributeValueBigInteger attributeValueBigInteger = AttributeValueBigInteger.of(BigInteger.valueOf(123));
        initialize(attributeValueBigInteger, AttributeValueBigInteger.class);
    }

    @Override
    public String name() {
        return "AttributeValueBigInteger";
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
