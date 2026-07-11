package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CommonAttributes;

import java.util.Collections;

public class CommonAttributesBenchmarkSubject extends KmipBenchmarkSubject<CommonAttributes> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public CommonAttributesBenchmarkSubject() throws Exception {
        CommonAttributes subject = CommonAttributes.of(Collections.emptyList());
        initialize(subject, CommonAttributes.class);
    }

    @Override
    public String name() {
        return "CommonAttributes";
    }
}
