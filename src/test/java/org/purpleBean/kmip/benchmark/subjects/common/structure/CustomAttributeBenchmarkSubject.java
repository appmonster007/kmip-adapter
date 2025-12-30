package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;

public class CustomAttributeBenchmarkSubject extends KmipBenchmarkSubject<CustomAttribute> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CustomAttributeBenchmarkSubject() throws Exception {
        CustomAttribute customAttribute = CustomAttribute.of("x-custom-state", AttributeValue.Enumeration.of(State.Standard.ACTIVE.getValue()));
        initialize(customAttribute, CustomAttribute.class);
    }

    @Override
    public String name() {
        return "CustomAttribute";
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
