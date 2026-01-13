package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ResultMessage;

public class ResultMessageBenchmarkSubject extends KmipBenchmarkSubject<ResultMessage> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ResultMessageBenchmarkSubject() throws Exception {
        ResultMessage resultMessage = ResultMessage.builder().value("Success").build();
        initialize(resultMessage, ResultMessage.class);
    }

    @Override
    public String name() {
        return "ResultMessage";
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