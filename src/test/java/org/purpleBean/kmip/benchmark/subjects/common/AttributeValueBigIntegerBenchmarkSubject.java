package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

import java.math.BigInteger;

public class AttributeValueBigIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.BigInteger> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueBigIntegerBenchmarkSubject() throws Exception {
        AttributeValue.BigInteger attributeValueBigInteger = AttributeValue.BigInteger.of(BigInteger.valueOf(123));
        initialize(attributeValueBigInteger, AttributeValue.BigInteger.class);
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
