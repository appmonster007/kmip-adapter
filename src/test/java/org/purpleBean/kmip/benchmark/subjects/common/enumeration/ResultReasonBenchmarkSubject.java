package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ResultReason;

public class ResultReasonBenchmarkSubject extends KmipBenchmarkSubject<ResultReason> {

    public ResultReasonBenchmarkSubject() throws Exception {
        ResultReason resultReason = new ResultReason(ResultReason.Standard.ITEM_NOT_FOUND);
        initialize(resultReason, ResultReason.class);
    }

    @Override
    public String name() {
        return "ResultReason";
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
