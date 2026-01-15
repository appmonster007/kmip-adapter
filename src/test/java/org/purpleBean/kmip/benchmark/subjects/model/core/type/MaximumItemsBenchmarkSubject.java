package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.MaximumItems;

public class MaximumItemsBenchmarkSubject extends KmipBenchmarkSubject<MaximumItems> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public MaximumItemsBenchmarkSubject() throws Exception {
        MaximumItems maximumItems = MaximumItems.builder().value(100).build();
        initialize(maximumItems, MaximumItems.class);
    }

    @Override
    public String name() {
        return "MaximumItems";
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