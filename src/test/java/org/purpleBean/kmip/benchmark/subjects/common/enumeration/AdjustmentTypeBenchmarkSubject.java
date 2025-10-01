package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeBenchmarkSubject extends KmipBenchmarkSubject<AdjustmentType> {

    public AdjustmentTypeBenchmarkSubject() throws Exception {
        AdjustmentType adjustmentType = new AdjustmentType(AdjustmentType.Standard.INCREMENT);
        initialize(adjustmentType, AdjustmentType.class);
    }

    @Override
    public String name() {
        return "AdjustmentType";
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
