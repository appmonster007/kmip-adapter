package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueTextStringBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.TextString> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueTextStringBenchmarkSubject() throws Exception {
        AttributeValue.TextString attributeValueTextString = AttributeValue.TextString.of("test");
        initialize(attributeValueTextString, AttributeValue.TextString.class);
    }

    @Override
    public String name() {
        return "AttributeValueTextString";
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
