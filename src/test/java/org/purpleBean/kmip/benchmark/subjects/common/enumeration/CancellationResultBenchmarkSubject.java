package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultBenchmarkSubject extends KmipBenchmarkSubject<CancellationResult> {

    public CancellationResultBenchmarkSubject() throws Exception {
        CancellationResult cancellationResult = CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
        initialize(cancellationResult, CancellationResult.class);
    }

    @Override
    public String name() {
        return "CancellationResult";
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
