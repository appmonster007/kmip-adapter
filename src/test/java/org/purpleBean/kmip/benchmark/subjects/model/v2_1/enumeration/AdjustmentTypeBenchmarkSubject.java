package org.purpleBean.kmip.benchmark.subjects.model.v2_1.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.AdjustmentType;

public class AdjustmentTypeBenchmarkSubject extends KmipBenchmarkSubject<AdjustmentType> {

    public AdjustmentTypeBenchmarkSubject() throws Exception {
        AdjustmentType adjustmentType = AdjustmentType.Standard.INCREMENT.inst();
        initialize(adjustmentType, AdjustmentType.class);
    }

    @Override
    public String name() {
        return "AdjustmentType";
    }

}
