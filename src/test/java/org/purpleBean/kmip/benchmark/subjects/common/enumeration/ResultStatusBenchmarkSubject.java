package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ResultStatus;

public class ResultStatusBenchmarkSubject extends KmipBenchmarkSubject<ResultStatus> {

    public ResultStatusBenchmarkSubject() throws Exception {
        ResultStatus resultStatus = new ResultStatus(ResultStatus.Standard.OPERATION_FAILED);
        initialize(resultStatus, ResultStatus.class);
    }

    @Override
    public String name() {
        return "ResultStatus";
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
