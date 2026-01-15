package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

public class AttributeValueByteStringBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueByteString> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueByteStringBenchmarkSubject() throws Exception {
        AttributeValueByteString attributeValueByteString = AttributeValueByteString.of(new byte[]{0x01, 0x02, 0x03});
        initialize(attributeValueByteString, AttributeValueByteString.class);
    }

    @Override
    public String name() {
        return "AttributeValueByteString";
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
