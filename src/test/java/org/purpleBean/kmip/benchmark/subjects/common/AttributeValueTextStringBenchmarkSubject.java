package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueTextString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueTextStringBenchmarkSubject() throws Exception {
        AttributeValueTextString attributeValueTextString = AttributeValueTextString.of("test");
        initialize(attributeValueTextString, AttributeValueTextString.class);
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
