package org.purpleBean.kmip.benchmark.subjects.common.structure;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.CustomAttribute;

public class CustomAttributeBenchmarkSubject extends KmipBenchmarkSubject<CustomAttribute> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public CustomAttributeBenchmarkSubject() throws Exception {
        State state = new State(State.Standard.ACTIVE);
        CustomAttribute customAttribute = CustomAttribute.of("x-custom-state", state);
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
