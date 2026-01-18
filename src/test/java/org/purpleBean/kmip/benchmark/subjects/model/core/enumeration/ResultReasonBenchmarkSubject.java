package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;

public class ResultReasonBenchmarkSubject extends KmipBenchmarkSubject<ResultReason> {

    public ResultReasonBenchmarkSubject() throws Exception {
        ResultReason resultReason = ResultReason.Standard.ITEM_NOT_FOUND.inst();
        initialize(resultReason, ResultReason.class);
    }

    @Override
    public String name() {
        return "ResultReason";
    }

}
