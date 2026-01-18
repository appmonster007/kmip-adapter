package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;

public class ResultStatusBenchmarkSubject extends KmipBenchmarkSubject<ResultStatus> {

    public ResultStatusBenchmarkSubject() throws Exception {
        ResultStatus resultStatus = ResultStatus.Standard.OPERATION_FAILED.inst();
        initialize(resultStatus, ResultStatus.class);
    }

    @Override
    public String name() {
        return "ResultStatus";
    }

}
