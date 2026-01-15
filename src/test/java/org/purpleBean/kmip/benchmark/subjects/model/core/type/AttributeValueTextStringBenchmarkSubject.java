package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

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
